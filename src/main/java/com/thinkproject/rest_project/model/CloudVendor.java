//CloudVendor.java
package com.thinkproject.rest_project.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cloud_vendors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CloudVendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vendor_id")
    private Long vendorId;

    @Column(name = "vendor_name", nullable = false)
    private String vendorName;

    @Column(name = "vendor_address", nullable = false)
    private String vendorAddress;

    @Column(name = "vendor_phone", nullable = false)
    private String vendorPhone;

    public CloudVendor(String vendorName, String vendorAddress, String vendorPhone) {
        this.vendorName = vendorName;
        this.vendorAddress = vendorAddress;
        this.vendorPhone = vendorPhone;
    }

    @OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL)
    private List<CloudService> services = new ArrayList<>();
    
}
