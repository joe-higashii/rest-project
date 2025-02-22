//CreateCloudServiceRequest.java
package com.thinkproject.rest_project.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateCloudServiceRequest {

    @NotBlank(message = "Nome do serviço não pode ser vazio")
    private String name;

    @NotBlank(message = "Descrição não pode ser vazia")
    private String description;

    @NotNull(message = "ID do fornecedor não pode ser nulo")
    private Long vendorId;

    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getDescription() {
        return description;
    }
 
    public void setDescription(String description) {
        this.description = description;
    }
 
    public Long getVendorId() {
        return vendorId;
    }
 
    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }
}

