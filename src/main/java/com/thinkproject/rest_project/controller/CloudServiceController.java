//CloudServiceController.java
package com.thinkproject.rest_project.controller;

import com.thinkproject.rest_project.model.CloudService;
import com.thinkproject.rest_project.service.CloudServiceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.thinkproject.rest_project.dto.CloudServiceDTO;
import com.thinkproject.rest_project.model.Client;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cloudservice")
@SecurityRequirement(name = "bearerAuth")
public class CloudServiceController {

    private final CloudServiceService cloudServiceService;

    @Operation(
        summary = "Listar todos os serviços",
        description = "Retorna uma lista de todos os serviços de nuvem cadastrados no sistema."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de serviços retornada com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping
    public List<CloudServiceDTO> getAllServices() {
        return cloudServiceService.getAllServicesAsDTO();
    }

    @Operation(
    summary = "Listar todos os clientes que utilizam um serviço",
    description = "Retorna uma lista de clientes que contrataram um serviço específico."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de clientes retornada com sucesso."),
        @ApiResponse(responseCode = "404", description = "Serviço não encontrado.")
    })
    @GetMapping("/{id}/clients")
    public List<Client> getClientsByServiceId(
    @Parameter(description = "ID do serviço", required = true) @PathVariable Long id) {
        return cloudServiceService.getClientsByServiceId(id);
    }

    @Operation(
        summary = "Criar um novo serviço de nuvem",
        description = "Adiciona um novo serviço ao sistema com as informações fornecidas."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Serviço criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Solicitação inválida (exemplo: campos obrigatórios ausentes)"),
        @ApiResponse(responseCode = "500", description = "Erro interno ao salvar o serviço")
    })
    @PostMapping
    public CloudService createService(
        @Parameter(description = "Detalhes do serviço a ser criado", required = true)
        @RequestBody CloudService cloudService) {
        return cloudServiceService.createService(cloudService);
    }
}

