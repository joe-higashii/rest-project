//CloudVendorAPIService.java
package com.thinkproject.rest_project.controller;

import java.util.Optional;

import com.thinkproject.rest_project.model.CloudVendor;
import com.thinkproject.rest_project.repository.CloudVendorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/cloudvendor")
public class CloudVendorAPIService {

    @Autowired
    private CloudVendorRepository cloudVendorRepository;

    @Operation(summary = "Buscar fornecedor pelo ID", description = "Recupera os detalhes de um fornecedor específico pelo seu ID.")
    @GetMapping("/{vendorId}")
    public CloudVendor getCloudVendorDetails(
        @Parameter(description = "ID do fornecedor que será buscado.", required = true)
        @PathVariable("vendorId") Long vendorId) {
        Optional<CloudVendor> cloudVendor = cloudVendorRepository.findById(vendorId);

        return cloudVendor.orElseThrow(() -> new RuntimeException("Vendor not found"));
    }

    @Operation(summary = "Criar um novo fornecedor", description = "Adiciona um novo fornecedor no banco de dados.")
    @PostMapping
    public CloudVendor createCloudVendor(
        @Parameter(description = "Detalhes do fornecedor que será criado.", required = true)
        @RequestBody CloudVendor cloudVendor) {
        return cloudVendorRepository.save(cloudVendor);
    }

    @Operation(summary = "Atualizar um fornecedor", description = "Atualiza as informações de um fornecedor existente pelo seu ID.")
    @PutMapping("/{vendorId}")
    public CloudVendor updateCloudVendor(
        @Parameter(description = "ID do fornecedor que será atualizado.", required = true)
        @PathVariable("vendorId") Long vendorId,
        @Parameter(description = "Novos detalhes do fornecedor.", required = true)
        @RequestBody CloudVendor cloudVendor) {
        if (!cloudVendorRepository.existsById(vendorId)) {
            throw new RuntimeException("Vendor not found");
        }

        cloudVendor = new CloudVendor(cloudVendor.getVendorName(),
                                      cloudVendor.getVendorAddress(),
                                      cloudVendor.getVendorPhone());
        cloudVendor.setVendorId(vendorId);

        return cloudVendorRepository.save(cloudVendor);
    }

    @Operation(summary = "Excluir fornecedor pelo ID", description = "Remove o fornecedor do banco de dados pelo seu ID.")
    @DeleteMapping("/{vendorId}")
    public String deleteCloudVendor(
        @Parameter(description = "ID do fornecedor que será excluído.", required = true)
        @PathVariable("vendorId") Long vendorId) {
        if (!cloudVendorRepository.existsById(vendorId)) {
            throw new RuntimeException("Vendor not found");
        }
        cloudVendorRepository.deleteById(vendorId);
        return "Vendor deleted successfully";
    }
}
