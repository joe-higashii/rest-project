package com.thinkproject.rest_project.config.documentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.*;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UsageContractControllerApiOperation {

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(summary = "Listar contratos de uso", description = "Retorna uma lista de contratos.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de contratos retornada com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public @interface GetAllContracts {}

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
        summary = "Criar um novo contrato de uso",
        description = "Cria um novo contrato de uso entre um cliente e um serviço.",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(
                examples = @ExampleObject(
                    name = "Exemplo de Contrato de Uso",
                    value = "{ \"clientId\": 1, \"serviceId\": 2, \"status\": \"ACTIVE\" }"
                )
            )
        )
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Contrato criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Solicitação inválida (exemplo: cliente ou serviço não encontrado)"),
        @ApiResponse(responseCode = "500", description = "Erro interno ao criar o contrato")
    })
    public @interface CreateContract {}


    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
        summary = "Listar contratos por status",
        description = "Retorna todos os contratos com o status fornecido (ex.: ACTIVE, CANCELLED)."
    )
    public @interface GetStatus {}

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
        summary = "Listar contratos por intervalo de datas",
        description = "Retorna todos os contratos cujo período coincide com o intervalo fornecido."
    )
    public @interface GetDateRange {}

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(summary = "Obter estatísticas de contratos", description = "Retorna a contagem de contratos agrupados por status.")
    public @interface GetStatistics {}

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(summary = "Listar serviços contratados por um cliente", description = "Retorna todos os serviços contratados por um cliente.")
    public @interface GetClientServices {}

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(summary = "Listar clientes que contrataram um serviço", description = "Retorna todos os clientes que contrataram um serviço específico.")
    public @interface GetServicesClients {}

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
        summary = "Atualizar um contrato existente",
        description = "Atualiza os detalhes de um contrato existente com base no ID."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Contrato atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Contrato não encontrado"),
        @ApiResponse(responseCode = "400", description = "Solicitação inválida"),
        @ApiResponse(responseCode = "500", description = "Erro interno ao atualizar o contrato")
    })
    public @interface UpdateContract {}

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
        summary = "Excluir um contrato",
        description = "Exclui um contrato existente pelo ID."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Contrato excluído com sucesso"),
        @ApiResponse(responseCode = "404", description = "Contrato não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno ao excluir o contrato")
    })
    public @interface DeleteContract {}
    
}
