//ClientController.java
package com.thinkproject.rest_project.controller;

import com.thinkproject.rest_project.model.Client;
import com.thinkproject.rest_project.dto.ClientDTO;
import com.thinkproject.rest_project.dto.request.CreateClientRequest;
import com.thinkproject.rest_project.model.CloudService;
import com.thinkproject.rest_project.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/clients")
@SecurityRequirement(name = "bearerAuth")
public class ClientController {

    private final ClientService clientService;

    @Operation(
        summary = "Listar todos os clientes paginados",
        description = "Retorna uma lista paginada de clientes cadastrados no sistema.",
        parameters = {
            @Parameter(name = "page", description = "Número da página (começa em 0)", example = "0"),
            @Parameter(name = "size", description = "Tamanho da página", example = "10"),
            @Parameter(name = "sort", description = "Critério de ordenação (ex: name,desc)", example = "name,asc")
        }
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de clientes retornada com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping //GET /clients?page=0&size=5&sort=name,asc
    public Page<ClientDTO> getAllClients(Pageable pageable) {
        log.info("Received request to get all clients with pagination");
        return clientService.getAllClientsAsDTO(pageable);
    }

    @Operation(
        summary = "Listar o cliente pelo ID",
        description = "Retorna um cliente cadastrado no sistema pelo ID."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente retornado com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/{id}")
    public ClientDTO getClientById(@PathVariable Long id) {
        log.info("Received request to get client with id {}", id);
        return clientService.getClientDTOById(id);
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
        @Valid @RequestBody CreateClientRequest request) {
            log.info("Received request to create a new client with data: {}", request);
            Client client = new Client();
            client.setName(request.getName());
            client.setEmail(request.getEmail());
            return clientService.createClient(client);
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
        log.info("Received request to get services for client with id {}", id);
        return clientService.getServicesByClientId(id);
    }
}

