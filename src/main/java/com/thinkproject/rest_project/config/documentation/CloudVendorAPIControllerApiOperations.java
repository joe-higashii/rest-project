package com.thinkproject.rest_project.config.documentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.lang.annotation.*;

/**
 * This file defines custom Swagger meta-annotations for the CloudVendor API Controller.
 * Each inner annotation encapsulates the Swagger documentation for a specific endpoint.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CloudVendorAPIControllerApiOperations {

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
        summary = "Buscar fornecedor pelo ID",
        description = "Recupera os detalhes de um fornecedor específico pelo seu ID."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Fornecedor encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Fornecedor não encontrado")
    })
    public @interface GetVendorDetails {}

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
        summary = "Criar um novo fornecedor",
        description = "Adiciona um novo fornecedor no banco de dados."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Fornecedor criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos para criação")
    })
    public @interface CreateVendor {}

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
        summary = "Atualizar um fornecedor",
        description = "Atualiza as informações de um fornecedor existente pelo seu ID."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Fornecedor atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Fornecedor não encontrado"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos para atualização")
    })
    public @interface UpdateVendor {}

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
        summary = "Excluir fornecedor pelo ID",
        description = "Remove o fornecedor do banco de dados pelo seu ID."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Fornecedor excluído com sucesso"),
        @ApiResponse(responseCode = "404", description = "Fornecedor não encontrado")
    })
    public @interface DeleteVendor {}
}
