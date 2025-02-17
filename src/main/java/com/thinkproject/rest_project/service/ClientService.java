//ClientService.java
package com.thinkproject.rest_project.service;

import com.thinkproject.rest_project.model.Client;
import com.thinkproject.rest_project.repository.ClientRepository;

import org.springframework.stereotype.Service;

import java.util.List;

import com.thinkproject.rest_project.model.CloudService;
import com.thinkproject.rest_project.model.UsageContract;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public List<CloudService> getServicesByClientId(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + clientId));
        
        // Obter os contratos do cliente e extrair os serviços associados
        return client.getContracts().stream()
                .map(UsageContract::getService)
                .toList();
    }    
}

