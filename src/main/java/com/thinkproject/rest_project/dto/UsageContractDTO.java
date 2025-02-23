//UsageContractDTO.java
package com.thinkproject.rest_project.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = true)
public class UsageContractDTO extends BaseDTO {
    @NotBlank(message = "O status do contrato não pode estar vazio.")
    private String status;
}

