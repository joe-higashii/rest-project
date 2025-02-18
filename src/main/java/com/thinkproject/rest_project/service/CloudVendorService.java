//CloudVendorService.java
package com.thinkproject.rest_project.service;

import com.thinkproject.rest_project.dto.CloudVendorDTO;
import com.thinkproject.rest_project.exception.ResourceNotFoundException;
import com.thinkproject.rest_project.model.CloudVendor;
import com.thinkproject.rest_project.repository.CloudVendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CloudVendorService {

    private final CloudVendorRepository cloudVendorRepository;

    public CloudVendorDTO getCloudVendorDTOById(Long id) {
        CloudVendor vendor = cloudVendorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fornecedor não encontrado com ID: " + id));
        return new CloudVendorDTO(vendor.getVendorId(), vendor.getVendorName(), vendor.getVendorAddress(), vendor.getVendorPhone());
    }

    public CloudVendor createVendor(CloudVendor cloudVendor) {
        return cloudVendorRepository.save(cloudVendor);
    }

    public CloudVendor updateVendor(Long vendorId, CloudVendor cloudVendor) {
        if (!cloudVendorRepository.existsById(vendorId)) {
            throw new ResourceNotFoundException("Fornecedor não encontrado com ID: " + vendorId);
        }
        cloudVendor.setVendorId(vendorId);
        return cloudVendorRepository.save(cloudVendor);
    }

    public void deleteVendor(Long vendorId) {
        if (!cloudVendorRepository.existsById(vendorId)) {
            throw new ResourceNotFoundException("Fornecedor não encontrado com ID: " + vendorId);
        }
        cloudVendorRepository.deleteById(vendorId);
    }
}

