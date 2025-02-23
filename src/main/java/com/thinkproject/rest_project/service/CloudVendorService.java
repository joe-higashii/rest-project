//CloudVendorService.java
package com.thinkproject.rest_project.service;

import com.thinkproject.rest_project.dto.CloudVendorDTO;
import com.thinkproject.rest_project.exception.ServiceException;
import com.thinkproject.rest_project.mapper.GenericMapper;
import com.thinkproject.rest_project.model.CloudVendor;
import com.thinkproject.rest_project.repository.CloudVendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CloudVendorService {

    private final CloudVendorRepository cloudVendorRepository;
    private final GenericMapper<CloudVendor, CloudVendorDTO> cloudVendorMapper;

    public CloudVendorDTO getCloudVendorDTOById(Long id) {
        CloudVendor vendor = cloudVendorRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Fornecedor não encontrado com ID: " + id));
        return cloudVendorMapper.toDto(vendor);
    }

    public CloudVendorDTO createVendor(CloudVendorDTO vendorDTO) {
        CloudVendor vendor = cloudVendorMapper.toEntity(vendorDTO);
        return cloudVendorMapper.toDto(cloudVendorRepository.save(vendor));
    }

    public CloudVendorDTO updateVendor(Long vendorId, CloudVendorDTO vendorDTO) {
        CloudVendor existingVendor = cloudVendorRepository.findById(vendorId)
                .orElseThrow(() -> new ServiceException("Fornecedor não encontrado com ID: " + vendorId));

        CloudVendor vendor = cloudVendorMapper.toEntity(vendorDTO);
        vendor.setVendorId(existingVendor.getVendorId());
        return cloudVendorMapper.toDto(cloudVendorRepository.save(vendor));
    }

    public void deleteVendor(Long vendorId) {
        CloudVendor vendor = cloudVendorRepository.findById(vendorId)
                .orElseThrow(() -> new ServiceException("Fornecedor não encontrado com ID: " + vendorId));
        cloudVendorRepository.delete(vendor);
    }
}

