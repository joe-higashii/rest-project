//ClientController.java
package com.thinkproject.rest_project.controller;

import com.thinkproject.rest_project.model.Client;
import com.thinkproject.rest_project.service.ClientService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.thinkproject.rest_project.model.CloudService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;

@RestController
@RequestMapping("/clients")
@SecurityRequirement(name = "bearerAuth")
public class ClientController {

    @Autowired
    private ClientService clientService;

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
        return clientService.getAllClients();
    }

    @Operation(
    summary = "Listar todos os serviços contratados por um cliente",
    description = "Retorna uma lista de serviços contratados por um cliente específico."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de serviços retornada com sucesso."),
        @ApiResponse(responseCode = "404", description = "Cliente não encontrado.")
    })
    @GetMapping("/{id}/services")
    public List<CloudService> getServicesByClientId(
        @Parameter(description = "ID do cliente", required = true) @PathVariable Long id) {
        return clientService.getServicesByClientId(id);
    }

    @Operation(
        summary = "Criar um novo cliente",
        description = "Adiciona um novo cliente ao sistema com as informações fornecidas.",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(
                examples = @ExampleObject(
                    name = "Exemplo de solicitação",
                    description = "Exemplo de criação de um cliente",
                    value = "{ \"name\": \"John Doe\", \"email\": \"johndoe@example.com\" }"
                )
            )
        )
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
        return clientService.createClient(client);
    }
}

