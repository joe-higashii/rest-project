//CloudServiceDTO.java
package com.thinkproject.rest_project.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = true)
public class CloudServiceDTO extends BaseDTO {
    @NotBlank(message = "O nome do serviço não pode estar vazio.")
    private String name;

    @NotBlank(message = "A descrição do serviço não pode estar vazia.")
    private String description;
}

