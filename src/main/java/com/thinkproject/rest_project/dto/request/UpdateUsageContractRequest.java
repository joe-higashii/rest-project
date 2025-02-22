//UpdateUsageContractRequest.java
package com.thinkproject.rest_project.dto.request;

import jakarta.validation.constraints.NotBlank;
import java.util.Date;

public class UpdateUsageContractRequest {

    // endDate pode ser opcional (sem validação)
    private Date endDate;

    @NotBlank(message = "Status é obrigatório")
    private String status;

    public Date getEndDate() {
        return endDate;
    }
 
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
 
    public String getStatus() {
        return status;
    }
 
    public void setStatus(String status) {
        this.status = status;
    }
}

