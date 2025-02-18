//UserDTO.java
package com.thinkproject.rest_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    private String username;
    private String role; // "USER" ou "ADMIN"
}

