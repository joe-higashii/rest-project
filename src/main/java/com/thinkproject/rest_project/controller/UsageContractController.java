//UsageContractController.java
package com.thinkproject.rest_project.controller;

import java.util.Date;

import com.thinkproject.rest_project.model.UsageContract;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.thinkproject.rest_project.service.UsageContractService;

import lombok.RequiredArgsConstructor;

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
    public List<UsageContract> getAllContracts() {
        return usageContractService.getAllContracts();
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
        @Parameter(description = "Data de início", required = true) @RequestParam Date startDate,
        @Parameter(description = "Data de término", required = true) @RequestParam Date endDate) {
        return usageContractService.getContractsByDateRange(startDate, endDate);
    }

    @Operation(
        summary = "Criar um novo contrato de uso",
        description = "Cria um novo contrato de uso entre um cliente e um serviço."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Contrato criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Solicitação inválida (exemplo: cliente ou serviço não encontrado)"),
        @ApiResponse(responseCode = "500", description = "Erro interno ao criar o contrato")
    })
    @PostMapping
    public ResponseEntity<?> createContract(
        @Parameter(description = "Detalhes do contrato a ser criado", required = true)
        @RequestBody UsageContract usageContract) {
            UsageContract createdContract = usageContractService.createContract(usageContract);
            return ResponseEntity.status(201).body(createdContract);
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
        @Parameter(description = "Novos detalhes do contrato", required = true) @RequestBody UsageContract usageContract) {
            UsageContract updatedContract = usageContractService.updateContract(id, usageContract);
            return ResponseEntity.ok(updatedContract);
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

