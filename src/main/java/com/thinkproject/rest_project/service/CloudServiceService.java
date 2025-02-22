//CloudServiceService.java
package com.thinkproject.rest_project.service;

import com.thinkproject.rest_project.dto.CloudServiceDTO;
import com.thinkproject.rest_project.exception.ResourceNotFoundException;
import com.thinkproject.rest_project.model.CloudService;
import com.thinkproject.rest_project.model.Client;
import com.thinkproject.rest_project.repository.CloudServiceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CloudServiceService {

    private final CloudServiceRepository cloudServiceRepository;
    private final ModelMapper modelMapper;

    public List<Client> getClientsByServiceId(Long serviceId) {
        log.info("Fetching clients for service with id {}", serviceId);
        CloudService service = cloudServiceRepository.findById(serviceId)
                .orElseThrow(() -> {
                    log.error("Service not found with id {}", serviceId);
                    return new ResourceNotFoundException("Serviço não encontrado com ID: " + serviceId);
                });
        List<Client> clients = service.getContracts().stream()
                .map(contract -> contract.getClient())
                .toList();
        log.info("Found {} clients for service {}", clients.size(), serviceId);
        return clients;
    }

    public List<CloudServiceDTO> getAllServicesAsDTO() {
        log.info("Fetching all cloud services as DTOs");
        List<CloudServiceDTO> dtos = cloudServiceRepository.findAll().stream()
                .map(service -> modelMapper.map(service, CloudServiceDTO.class))
                .toList();
        log.info("Found {} cloud services", dtos.size());
        return dtos;
    }
    
    public CloudServiceDTO getCloudServiceDTOById(Long id) {
        log.info("Fetching cloud service with id {}", id);
        CloudService service = cloudServiceRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Cloud service not found with id {}", id);
                    return new ResourceNotFoundException("Serviço não encontrado com ID: " + id);
                });
        CloudServiceDTO dto = modelMapper.map(service, CloudServiceDTO.class);
        log.info("Fetched cloud service: {}", dto);
        return dto;
    }

    public CloudService createService(CloudService cloudService) {
        log.info("Creating new cloud service: {}", cloudService);
        CloudService savedService = cloudServiceRepository.save(cloudService);
        log.info("Created cloud service with id {}", savedService.getId());
        return savedService;
    }
}

