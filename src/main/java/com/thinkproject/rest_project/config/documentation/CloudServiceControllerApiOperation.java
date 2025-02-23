//CloudServiceControllerApiOperation.java
package com.thinkproject.rest_project.config.documentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CloudServiceControllerApiOperation {

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(summary = "Listar todos os serviços de nuvem", description = "Retorna uma lista de serviços.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de serviços retornada com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public @interface GetAllServices {}

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(summary = "Criar um novo serviço", description = "Adiciona um novo serviço de nuvem.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Serviço criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Solicitação inválida")
    })
    public @interface CreateService {}

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
        summary = "Listar todos os clientes que utilizam um serviço",
        description = "Retorna uma lista de clientes que contrataram um serviço específico."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de clientes retornada com sucesso."),
        @ApiResponse(responseCode = "404", description = "Serviço não encontrado.")
    })
    public @interface GetClientServices {}
}

