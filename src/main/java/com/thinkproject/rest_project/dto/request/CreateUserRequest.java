//CreateUserRequest.java
package com.thinkproject.rest_project.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateUserRequest {

    @NotBlank(message = "Username não pode ser vazio")
    private String username;

    @NotBlank(message = "Password não pode ser vazio")
    @Size(min = 6, message = "Password deve ter pelo menos 6 caracteres")
    private String password;

    @NotBlank(message = "Role não pode ser vazio")
    private String role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    public String getRole() {
        return role;
    }
 
    public void setRole(String role) {
        this.role = role;
    }
}

