//ClientDTO.java
package com.thinkproject.rest_project.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {
    @NotNull(message = "ID não pode ser nulo.")
    private Long id;

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

    public ClientDTO(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    
}

