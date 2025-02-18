//UserDTO.java
package com.thinkproject.rest_project.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String username;
    private String role; // "USER" ou "ADMIN"
}

