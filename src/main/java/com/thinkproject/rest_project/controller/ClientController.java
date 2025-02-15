package com.thinkproject.rest_project.controller;

import com.thinkproject.rest_project.model.Client;
import com.thinkproject.rest_project.repository.ClientRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/clients")
@SecurityRequirement(name = "bearerAuth")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @Operation(
        summary = "Listar todos os clientes",
        description = "Retorna uma lista de todos os clientes cadastrados no sistema."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de clientes retornada com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Operation(
        summary = "Criar um novo cliente",
        description = "Adiciona um novo cliente ao sistema com as informações fornecidas."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Solicitação inválida (exemplo: campos obrigatórios ausentes)"),
        @ApiResponse(responseCode = "500", description = "Erro interno ao salvar o cliente")
    })
    @PostMapping
    public Client createClient(
        @Parameter(description = "Detalhes do cliente a ser criado", required = true)
        @RequestBody Client client) {
        return clientRepository.save(client);
    }
}
