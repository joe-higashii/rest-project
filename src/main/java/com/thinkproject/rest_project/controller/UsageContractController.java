//UsageContractController.java
package com.thinkproject.rest_project.controller;

import com.thinkproject.rest_project.config.documentation.UsageContractControllerApiOperation.*;
import com.thinkproject.rest_project.model.UsageContract;
import com.thinkproject.rest_project.model.Client;
import com.thinkproject.rest_project.model.CloudService;
import com.thinkproject.rest_project.dto.UsageContractDTO;
import com.thinkproject.rest_project.dto.request.CreateUsageContractRequest;
import com.thinkproject.rest_project.dto.request.UpdateUsageContractRequest;
import com.thinkproject.rest_project.service.UsageContractService;
import io.swagger.v3.oas.annotations.Parameter;
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

    @GetAllContracts
    @GetMapping
    public List<UsageContractDTO> getAllContracts() {
        return usageContractService.getAllUsageContractsAsDTO();
    }

    @GetStatus
    @GetMapping("/status/{status}")
    public List<UsageContract> getContractsByStatus(
        @Parameter(description = "Status do contrato", required = true) @PathVariable String status) {
        return usageContractService.getContractsByStatus(status);
    }

    @GetDateRange
    @GetMapping("/date-range")
    public List<UsageContract> getContractsByDateRange(
        @Parameter(description = "Data de início (formato yyyy-MM-dd)", required = true) @RequestParam String startDate,
        @Parameter(description = "Data de término (formato yyyy-MM-dd)", required = true) @RequestParam String endDate) {
        // Conversão simples de String para Date (poderia ser aprimorada)
        return usageContractService.getContractsByDateRange(java.sql.Date.valueOf(startDate), java.sql.Date.valueOf(endDate));
    }

    @GetStatistics
    @GetMapping("/statistics")
    public List<Object[]> getContractStatistics() {
        return usageContractService.getContractStatistics();
    }

    @GetClientServices
    @GetMapping("/clients/{clientId}/services")
    public List<CloudService> getServicesByClient(
            @Parameter(description = "ID do cliente", required = true) @PathVariable Long clientId) {
        return usageContractService.getServicesByClient(clientId);
    }

    @GetServicesClients
    @GetMapping("/services/{serviceId}/clients")
    public List<Client> getClientsByService(
            @Parameter(description = "ID do serviço", required = true) @PathVariable Long serviceId) {
        return usageContractService.getClientsByService(serviceId);
    }

    @CreateContract
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

    @UpdateContract    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateContract(
        @Parameter(description = "ID do contrato a ser atualizado", required = true) @PathVariable Long id,
        @Parameter(description = "Novos detalhes do contrato", required = true)
        @Valid @RequestBody UpdateUsageContractRequest request) {
            UsageContract updated = usageContractService.updateContract(id, request.getStatus(), request.getEndDate());
            return ResponseEntity.ok(updated);
    }

    @DeleteContract
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContract(
        @Parameter(description = "ID do contrato a ser excluído", required = true) @PathVariable Long id) {
            usageContractService.deleteContract(id);
            return ResponseEntity.ok("Contrato excluído com sucesso.");
    }
}

