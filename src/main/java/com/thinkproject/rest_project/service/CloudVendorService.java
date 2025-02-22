//CloudVendorService.java
package com.thinkproject.rest_project.service;

import com.thinkproject.rest_project.dto.CloudVendorDTO;
import com.thinkproject.rest_project.exception.ResourceNotFoundException;
import com.thinkproject.rest_project.model.CloudVendor;
import com.thinkproject.rest_project.repository.CloudVendorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class CloudVendorService {

    private final CloudVendorRepository cloudVendorRepository;
    private final ModelMapper modelMapper;

    public CloudVendorDTO getCloudVendorDTOById(Long id) {
        log.info("Fetching cloud vendor with id {}", id);
        CloudVendor vendor = cloudVendorRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Cloud vendor not found with id {}", id);
                    return new ResourceNotFoundException("Fornecedor não encontrado com ID: " + id);
                });
        CloudVendorDTO dto = modelMapper.map(vendor, CloudVendorDTO.class);
        log.info("Fetched cloud vendor: {}", dto);
        return dto;
    }

    public CloudVendor createVendor(CloudVendor cloudVendor) {
        log.info("Creating new cloud vendor: {}", cloudVendor);
        CloudVendor savedVendor = cloudVendorRepository.save(cloudVendor);
        log.info("Created cloud vendor with id {}", savedVendor.getVendorId());
        return savedVendor;
    }

    public CloudVendor updateVendor(Long vendorId, CloudVendor cloudVendor) {
        log.info("Updating cloud vendor with id {}", vendorId);
        if (!cloudVendorRepository.existsById(vendorId)) {
            log.error("Cloud vendor not found with id {}", vendorId);
            throw new ResourceNotFoundException("Fornecedor não encontrado com ID: " + vendorId);
        }
        cloudVendor.setVendorId(vendorId);
        CloudVendor updatedVendor = cloudVendorRepository.save(cloudVendor);
        log.info("Updated cloud vendor: {}", updatedVendor);
        return updatedVendor;
    }

    public void deleteVendor(Long vendorId) {
        log.info("Deleting cloud vendor with id {}", vendorId);
        if (!cloudVendorRepository.existsById(vendorId)) {
            log.error("Cloud vendor not found with id {}", vendorId);
            throw new ResourceNotFoundException("Fornecedor não encontrado com ID: " + vendorId);
        }
        cloudVendorRepository.deleteById(vendorId);
        log.info("Deleted cloud vendor with id {}", vendorId);
    }
}

