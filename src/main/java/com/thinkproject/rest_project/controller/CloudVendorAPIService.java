//CloudVendorAPIService.java
package com.thinkproject.rest_project.controller;

import com.thinkproject.rest_project.model.CloudVendor;
import com.thinkproject.rest_project.dto.CloudVendorDTO;
import com.thinkproject.rest_project.dto.request.CreateCloudVendorRequest;
import com.thinkproject.rest_project.dto.request.UpdateCloudVendorRequest;
import com.thinkproject.rest_project.service.CloudVendorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cloudvendor")
@SecurityRequirement(name = "bearerAuth")
public class CloudVendorAPIService {

    private final CloudVendorService cloudVendorService;

    @Operation(summary = "Buscar fornecedor pelo ID", description = "Recupera os detalhes de um fornecedor específico pelo seu ID.")
    @GetMapping("/{vendorId}")
    public CloudVendorDTO getCloudVendorDetails(
        @Parameter(description = "ID do fornecedor que será buscado.", required = true)
        @PathVariable("vendorId") Long vendorId) {
        return cloudVendorService.getCloudVendorDTOById(vendorId);
    }

    @Operation(summary = "Criar um novo fornecedor", description = "Adiciona um novo fornecedor no banco de dados.")
    @PostMapping
    public CloudVendor createCloudVendor(
        @Parameter(description = "Detalhes do fornecedor que será criado.", required = true)
        @Valid @RequestBody CreateCloudVendorRequest request) {
            CloudVendor vendor = new CloudVendor();
            vendor.setVendorName(request.getVendorName());
            vendor.setVendorAddress(request.getVendorAddress());
            vendor.setVendorPhone(request.getVendorPhone());
            return cloudVendorService.createVendor(vendor);
    }

    @Operation(summary = "Atualizar um fornecedor", description = "Atualiza as informações de um fornecedor existente pelo seu ID.")
    @PutMapping("/{vendorId}")
    public CloudVendor updateCloudVendor(
        @Parameter(description = "ID do fornecedor que será atualizado.", required = true)
        @PathVariable("vendorId") Long vendorId,
        @Parameter(description = "Novos detalhes do fornecedor.", required = true)
        @Valid @RequestBody UpdateCloudVendorRequest request) {
            CloudVendor vendor = new CloudVendor();
            vendor.setVendorName(request.getVendorName());
            vendor.setVendorAddress(request.getVendorAddress());
            vendor.setVendorPhone(request.getVendorPhone());
            return cloudVendorService.updateVendor(vendorId, vendor);
    }

    @Operation(summary = "Excluir fornecedor pelo ID", description = "Remove o fornecedor do banco de dados pelo seu ID.")
    @DeleteMapping("/{vendorId}")
    public String deleteCloudVendor(
        @Parameter(description = "ID do fornecedor que será excluído.", required = true)
        @PathVariable("vendorId") Long vendorId) {
        cloudVendorService.deleteVendor(vendorId);
        return "Vendor deleted successfully";
    }
}

