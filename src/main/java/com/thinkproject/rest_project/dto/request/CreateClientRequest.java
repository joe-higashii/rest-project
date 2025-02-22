//CreateClientRequest.java
package com.thinkproject.rest_project.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class CreateClientRequest {

    @NotBlank(message = "Nome não pode ser vazio")
    private String name;

    @NotBlank(message = "Email não pode ser vazio")
    @Email(message = "Email inválido")
    private String email;

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
}

