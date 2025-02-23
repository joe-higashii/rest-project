//CloudServiceService.java
package com.thinkproject.rest_project.service;

import com.thinkproject.rest_project.dto.CloudServiceDTO;
import com.thinkproject.rest_project.exception.ServiceException;
import com.thinkproject.rest_project.mapper.GenericMapper;
import com.thinkproject.rest_project.model.CloudService;
import com.thinkproject.rest_project.model.Client;
import com.thinkproject.rest_project.repository.CloudServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CloudServiceService {

    private final CloudServiceRepository cloudServiceRepository;
    private final GenericMapper<CloudService, CloudServiceDTO> cloudServiceMapper;

    public List<Client> getClientsByServiceId(Long serviceId) {
        CloudService service = cloudServiceRepository.findById(serviceId)
                .orElseThrow(() -> new ServiceException("Serviço não encontrado com ID: " + serviceId));
        return service.getContracts().stream()
                .map(contract -> contract.getClient())
                .toList();
    }

    public List<CloudServiceDTO> getAllServicesAsDTO() {
        return cloudServiceRepository.findAll().stream()
                .map(cloudServiceMapper::toDto)
                .toList();
    }

    public CloudServiceDTO getCloudServiceDTOById(Long id) {
        CloudService service = cloudServiceRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Serviço não encontrado com ID: " + id));
        return cloudServiceMapper.toDto(service);
    }

    public CloudService createService(CloudService cloudService) {
        return cloudServiceRepository.save(cloudService);
    }
}

