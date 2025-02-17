//CloudServiceService.java
package com.thinkproject.rest_project.service;

import com.thinkproject.rest_project.model.CloudService;
import com.thinkproject.rest_project.repository.CloudServiceRepository;

import org.springframework.stereotype.Service;

import java.util.List;

import com.thinkproject.rest_project.model.Client;
import com.thinkproject.rest_project.model.UsageContract;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CloudServiceService {

    private final CloudServiceRepository cloudServiceRepository;

    public List<CloudService> getAllServices() {
        return cloudServiceRepository.findAll();
    }

    public CloudService createService(CloudService cloudService) {
        return cloudServiceRepository.save(cloudService);
    }

    public List<Client> getClientsByServiceId(Long serviceId) {
        CloudService service = cloudServiceRepository.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado com ID: " + serviceId));
        
        // Obter os contratos do serviço e extrair os clientes associados
        return service.getContracts().stream()
                .map(UsageContract::getClient)
                .toList();
    }    
}

