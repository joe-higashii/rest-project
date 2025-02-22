//ClientService.java
package com.thinkproject.rest_project.service;

import com.thinkproject.rest_project.dto.ClientDTO;
import com.thinkproject.rest_project.exception.ResourceNotFoundException;
import com.thinkproject.rest_project.model.Client;
import com.thinkproject.rest_project.model.CloudService;
import com.thinkproject.rest_project.model.UsageContract;
import com.thinkproject.rest_project.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final ModelMapper modelMapper;

    @Cacheable(value = "clients", key = "#pageable.pageNumber")
    public Page<ClientDTO> getAllClientsAsDTO(Pageable pageable) {
        log.info("Fetching all clients with pagination: page {}, size {}", pageable.getPageNumber(), pageable.getPageSize());
        return clientRepository.findAll(pageable).map(client -> modelMapper.map(client, ClientDTO.class));
    }
    
    public ClientDTO getClientDTOById(Long id) {
        log.info("Fetching client with id {}", id);
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Client not found with id {}", id);
                    return new ResourceNotFoundException("Cliente não encontrado com ID: " + id);
                });
        ClientDTO dto = modelMapper.map(client, ClientDTO.class);
        log.info("Fetched client: {}", dto);
        return dto;
    }

    public Client createClient(Client client) {
        log.info("Creating new client: {}", client);
        Client savedClient = clientRepository.save(client);
        log.info("Created client with id {}", savedClient.getId());
        return savedClient;
    }

    public List<CloudService> getServicesByClientId(Long clientId) {
        log.info("Fetching services for client with id {}", clientId);
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> {
                    log.error("Client not found with id {}", clientId);
                    return new ResourceNotFoundException("Cliente não encontrado com ID: " + clientId);
                });
        List<CloudService> services = client.getContracts().stream()
                .map(UsageContract::getService)
                .toList();
        log.info("Found {} services for client {}", services.size(), clientId);
        return services;
    }    
}

