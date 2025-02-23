//ClientService.java
package com.thinkproject.rest_project.service;

import com.thinkproject.rest_project.dto.ClientDTO;
import com.thinkproject.rest_project.exception.ServiceException;
import com.thinkproject.rest_project.mapper.GenericMapper;
import com.thinkproject.rest_project.model.Client;
import com.thinkproject.rest_project.model.CloudService;
import com.thinkproject.rest_project.model.UsageContract;
import com.thinkproject.rest_project.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final GenericMapper<Client, ClientDTO> clientMapper;

    @Cacheable(value = "clients", key = "#pageable.pageNumber")
    public Page<ClientDTO> getAllClientsAsDTO(Pageable pageable) {
        return clientRepository.findAll(pageable).map(clientMapper::toDto);
    }

    public ClientDTO getClientDTOById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Cliente não encontrado com ID: " + id));
        return clientMapper.toDto(client);
    }

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public List<CloudService> getServicesByClientId(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ServiceException("Cliente não encontrado com ID: " + clientId));
        return client.getContracts().stream()
                .map(UsageContract::getService)
                .toList();
    }
}

