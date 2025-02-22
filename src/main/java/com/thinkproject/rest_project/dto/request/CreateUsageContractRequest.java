//CreateUsageContractRequest.java
package com.thinkproject.rest_project.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateUsageContractRequest {

    @NotNull(message = "ID do cliente é obrigatório")
    private Long clientId;

    @NotNull(message = "ID do serviço é obrigatório")
    private Long serviceId;

    @NotBlank(message = "Status é obrigatório")
    private String status;

    public Long getClientId() {
        return clientId;
    }
 
    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
 
    public Long getServiceId() {
        return serviceId;
    }
 
    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }
 
    public String getStatus() {
        return status;
    }
 
    public void setStatus(String status) {
        this.status = status;
    }
}

