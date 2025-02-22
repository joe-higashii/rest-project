//UsageContractController.java
package com.thinkproject.rest_project.controller;

import com.thinkproject.rest_project.model.UsageContract;
import com.thinkproject.rest_project.model.Client;
import com.thinkproject.rest_project.model.CloudService;
import com.thinkproject.rest_project.dto.UsageContractDTO;
import com.thinkproject.rest_project.dto.request.CreateUsageContractRequest;
import com.thinkproject.rest_project.dto.request.UpdateUsageContractRequest;
import com.thinkproject.rest_project.service.UsageContractService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/usage-contracts")
@SecurityRequirement(name = "bearerAuth")
public class UsageContractController {

    private final UsageContractService usageContractService;

    @Operation(
        summary = "Listar todos os contratos de uso",
        description = "Retorna uma lista de todos os contratos de uso registrados no sistema."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de contratos retornada com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping
    public List<UsageContractDTO> getAllContracts() {
        return usageContractService.getAllUsageContractsAsDTO();
    }

    @Operation(
        summary = "Listar contratos por status",
        description = "Retorna todos os contratos com o status fornecido (ex.: ACTIVE, CANCELLED)."
    )
    @GetMapping("/status/{status}")
    public List<UsageContract> getContractsByStatus(
        @Parameter(description = "Status do contrato", required = true) @PathVariable String status) {
        return usageContractService.getContractsByStatus(status);
    }

    @Operation(
        summary = "Listar contratos por intervalo de datas",
        description = "Retorna todos os contratos cujo período coincide com o intervalo fornecido."
    )
    @GetMapping("/date-range")
    public List<UsageContract> getContractsByDateRange(
        @Parameter(description = "Data de início (formato yyyy-MM-dd)", required = true) @RequestParam String startDate,
        @Parameter(description = "Data de término (formato yyyy-MM-dd)", required = true) @RequestParam String endDate) {
        // Conversão simples de String para Date (poderia ser aprimorada)
        return usageContractService.getContractsByDateRange(java.sql.Date.valueOf(startDate), java.sql.Date.valueOf(endDate));
    }

    @Operation(summary = "Obter estatísticas de contratos", description = "Retorna a contagem de contratos agrupados por status.")
    @GetMapping("/statistics")
    public List<Object[]> getContractStatistics() {
        return usageContractService.getContractStatistics();
    }

    @Operation(summary = "Listar serviços contratados por um cliente", description = "Retorna todos os serviços contratados por um cliente.")
    @GetMapping("/clients/{clientId}/services")
    public List<CloudService> getServicesByClient(
            @Parameter(description = "ID do cliente", required = true) @PathVariable Long clientId) {
        return usageContractService.getServicesByClient(clientId);
    }

    @Operation(summary = "Listar clientes que contrataram um serviço", description = "Retorna todos os clientes que contrataram um serviço específico.")
    @GetMapping("/services/{serviceId}/clients")
    public List<Client> getClientsByService(
            @Parameter(description = "ID do serviço", required = true) @PathVariable Long serviceId) {
        return usageContractService.getClientsByService(serviceId);
    }

    @Operation(
        summary = "Criar um novo contrato de uso",
        description = "Cria um novo contrato de uso entre um cliente e um serviço.",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(
                examples = @ExampleObject(
                    name = "Exemplo de Contrato de Uso",
                    value = "{ \"clientId\": 1, \"serviceId\": 2, \"status\": \"ACTIVE\" }"
                )
            )
        )
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Contrato criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Solicitação inválida (exemplo: cliente ou serviço não encontrado)"),
        @ApiResponse(responseCode = "500", description = "Erro interno ao criar o contrato")
    })
    @PostMapping
    public ResponseEntity<?> createContract(
        @Parameter(description = "Detalhes do contrato a ser criado", required = true)
        @Valid @RequestBody CreateUsageContractRequest request) {
            UsageContract usageContract = new UsageContract();
            usageContract.setStatus(request.getStatus());
            // A lógica de associar Client e CloudService a partir dos IDs é delegada ao service
            UsageContract created = usageContractService.createContract(usageContract, request.getClientId(), request.getServiceId());
            return ResponseEntity.status(201).body(created);
    }

    @Operation(
        summary = "Atualizar um contrato existente",
        description = "Atualiza os detalhes de um contrato existente com base no ID."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Contrato atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Contrato não encontrado"),
        @ApiResponse(responseCode = "400", description = "Solicitação inválida"),
        @ApiResponse(responseCode = "500", description = "Erro interno ao atualizar o contrato")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateContract(
        @Parameter(description = "ID do contrato a ser atualizado", required = true) @PathVariable Long id,
        @Parameter(description = "Novos detalhes do contrato", required = true)
        @Valid @RequestBody UpdateUsageContractRequest request) {
            UsageContract updated = usageContractService.updateContract(id, request.getStatus(), request.getEndDate());
            return ResponseEntity.ok(updated);
    }

    @Operation(
        summary = "Excluir um contrato",
        description = "Exclui um contrato existente pelo ID."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Contrato excluído com sucesso"),
        @ApiResponse(responseCode = "404", description = "Contrato não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno ao excluir o contrato")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContract(
        @Parameter(description = "ID do contrato a ser excluído", required = true) @PathVariable Long id) {
            usageContractService.deleteContract(id);
            return ResponseEntity.ok("Contrato excluído com sucesso.");
    }
}

