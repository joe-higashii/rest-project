//CloudVendorDTO.java
package com.thinkproject.rest_project.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CloudVendorDTO {
    private Long vendorId;
    private String vendorName;
    private String vendorAddress;
    private String vendorPhone;

    public CloudVendorDTO(String vendorName, String vendorAddress, String vendorPhone) {
        this.vendorName = vendorName;
        this.vendorAddress = vendorAddress;
        this.vendorPhone = vendorPhone;
    }
}

