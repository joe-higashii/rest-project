//CloudVendorService.java
package com.thinkproject.rest_project.service;

import com.thinkproject.rest_project.model.CloudVendor;
import com.thinkproject.rest_project.repository.CloudVendorRepository;

import org.springframework.stereotype.Service;

import com.thinkproject.rest_project.dto.CloudVendorDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CloudVendorService {

    private final CloudVendorRepository cloudVendorRepository;

    public CloudVendorDTO getCloudVendorDTOById(Long id) {
        CloudVendor vendor = cloudVendorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado com ID: " + id));
        return new CloudVendorDTO(vendor.getVendorId(), vendor.getVendorName(), vendor.getVendorAddress(), vendor.getVendorPhone());
    }

    public CloudVendor createVendor(CloudVendor cloudVendor) {
        return cloudVendorRepository.save(cloudVendor);
    }

    public CloudVendor updateVendor(Long vendorId, CloudVendor cloudVendor) {
        if (!cloudVendorRepository.existsById(vendorId)) {
            throw new RuntimeException("Vendor not found");
        }

        cloudVendor.setVendorId(vendorId);
        return cloudVendorRepository.save(cloudVendor);
    }

    public void deleteVendor(Long vendorId) {
        if (!cloudVendorRepository.existsById(vendorId)) {
            throw new RuntimeException("Vendor not found");
        }
        cloudVendorRepository.deleteById(vendorId);
    }
}

