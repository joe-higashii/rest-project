//CloudServiceDTO.java
package com.thinkproject.rest_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CloudServiceDTO {
    private Long id;
    private String name;
    private String description;
}

