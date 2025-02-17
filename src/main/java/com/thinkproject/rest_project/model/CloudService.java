//CloudService.java
package com.thinkproject.rest_project.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cloud_services")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CloudService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private Long id;

    @Column(name = "service_name", nullable = false)
    private String name;

    @Column(name = "service_description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "vendor_id", nullable = false)
    private CloudVendor vendor; // Relacionamento Many-to-One

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<UsageContract> contracts = new ArrayList<>();

}

