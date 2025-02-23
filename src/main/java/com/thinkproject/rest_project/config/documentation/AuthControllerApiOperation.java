package com.thinkproject.rest_project.config.documentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthControllerApiOperation {

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
        summary = "Registrar um novo usuário",
        description = "Permite registrar um novo usuário no sistema, fornecendo um username, senha e papel (USER ou ADMIN).",
        requestBody = @RequestBody(
            content = @Content(
                examples = @ExampleObject(
                    name = "Exemplo de Registro",
                    value = "{ \"username\": \"johndoe\", \"password\": \"secret123\", \"role\": \"USER\" }"
                )
            )
        )
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário registrado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Username já existe ou papel inválido")
    })
    public @interface Register {}

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
        summary = "Fazer login",
        description = "Autentica o usuário no sistema e retorna um token JWT para ser usado em endpoints protegidos.",
        requestBody = @RequestBody(
            content = @Content(
                examples = @ExampleObject(
                    name = "Exemplo de Login",
                    value = "{ \"username\": \"johndoe\", \"password\": \"secret123\" }"
                )
            )
        )
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Login realizado com sucesso"),
        @ApiResponse(responseCode = "401", description = "Usuário ou senha inválidos")
    })
    public @interface Login {}

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
        summary = "Renovar token JWT",
        description = "Permite renovar um token JWT válido antes de ele expirar, retornando um novo token atualizado."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Token renovado com sucesso"),
        @ApiResponse(responseCode = "401", description = "Token inválido ou expirado")
    })
    public @interface RenewToken {}
}
