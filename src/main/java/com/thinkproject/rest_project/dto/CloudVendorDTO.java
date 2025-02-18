//CloudVendorDTO.java
package com.thinkproject.rest_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CloudVendorDTO {
    private Long vendorId;
    private String vendorName;
    private String vendorAddress;
    private String vendorPhone;
}

