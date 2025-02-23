//ClientDTO.java
package com.thinkproject.rest_project.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ClientDTO extends BaseDTO {
    @NotBlank(message = "Nome não pode ser vazio.")
    @Size(min = 5, max = 100, message = "Nome deve ter entre 5 e 100 caracteres.")
    private String name;

    @NotBlank(message = "Email não pode ser vazio.")
    @Email(message = "O email fornecido é inválido.")
    private String email;

    @NotNull(message = "Idade não pode ser nula.")
    private Integer age;

    @NotBlank(message = "Endereço não pode ser vazio.")
    private String address;
}

