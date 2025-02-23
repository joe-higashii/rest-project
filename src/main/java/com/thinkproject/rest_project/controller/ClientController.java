//ClientController.java
package com.thinkproject.rest_project.controller;

import com.thinkproject.rest_project.model.Client;
import com.thinkproject.rest_project.dto.ClientDTO;
import com.thinkproject.rest_project.dto.request.CreateClientRequest;
import com.thinkproject.rest_project.model.CloudService;
import com.thinkproject.rest_project.service.ClientService;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thinkproject.rest_project.config.documentation.ClientControllerApiOperation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/clients")
@SecurityRequirement(name = "bearerAuth")
public class ClientController {

    private final ClientService clientService;

    @GetAllClients
    @GetMapping //GET /clients?page=0&size=5&sort=name,asc
    public Page<ClientDTO> getAllClients(Pageable pageable) {
        log.info("Received request to get all clients with pagination");
        return clientService.getAllClientsAsDTO(pageable);
    }

    @GetClientById
    @GetMapping("/{id}")
    public ClientDTO getClientById(@PathVariable Long id) {
        log.info("Received request to get client with id {}", id);
        return clientService.getClientDTOById(id);
    }

    @CreateClient
    @PostMapping
    public Client createClient(
        @Parameter(description = "Detalhes do cliente a ser criado", required = true)
        @Valid @RequestBody CreateClientRequest request) {
            log.info("Received request to create a new client with data: {}", request);
            Client client = new Client();
            client.setName(request.getName());
            client.setEmail(request.getEmail());
            return clientService.createClient(client);
    }

    @GetClientServices
    @GetMapping("/{id}/services")
    public List<CloudService> getServicesByClientId(
        @Parameter(description = "ID do cliente", required = true) @PathVariable Long id) {
        log.info("Received request to get services for client with id {}", id);
        return clientService.getServicesByClientId(id);
    }
}

