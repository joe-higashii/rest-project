//UsageContractDTO.java
package com.thinkproject.rest_project.dto;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsageContractDTO {
    private Long id;
    private String status;
    private Date startDate;
    private Date endDate;
    private ClientDTO client;
    private CloudServiceDTO service;
}

