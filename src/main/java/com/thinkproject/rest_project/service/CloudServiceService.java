//CloudServiceService.java
package com.thinkproject.rest_project.service;

import com.thinkproject.rest_project.model.CloudService;
import com.thinkproject.rest_project.repository.CloudServiceRepository;

import org.springframework.stereotype.Service;

import java.util.List;

import com.thinkproject.rest_project.dto.CloudServiceDTO;
import com.thinkproject.rest_project.model.Client;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CloudServiceService {

    private final CloudServiceRepository cloudServiceRepository;

    public List<Client> getClientsByServiceId(Long serviceId) {
        CloudService service = cloudServiceRepository.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado com ID: " + serviceId));
        
        return service.getContracts().stream()
                .map(contract -> contract.getClient())
                .toList();
    }

    public List<CloudServiceDTO> getAllServicesAsDTO() {
        return cloudServiceRepository.findAll().stream()
                .map(service -> new CloudServiceDTO(service.getId(), service.getName(), service.getDescription()))
                .toList();
    }
    
    public CloudServiceDTO getCloudServiceDTOById(Long id) {
        CloudService service = cloudServiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado com ID: " + id));
        return new CloudServiceDTO(service.getId(), service.getName(), service.getDescription());
    }

    public CloudService createService(CloudService cloudService) {
        return cloudServiceRepository.save(cloudService);
    }
}

