//AuthController.java
package com.thinkproject.rest_project.controller;

import com.thinkproject.rest_project.model.User;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

import com.thinkproject.rest_project.service.AuthService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Operation(
        summary = "Registrar um novo usuário",
        description = "Permite registrar um novo usuário no sistema, fornecendo um username, senha e papel (USER ou ADMIN)."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário registrado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Username já existe ou papel inválido")
    })
    @PostMapping("/register")
    public Map<String, String> register(
        @Parameter(description = "Detalhes do usuário para registro (username, password, role)", required = true)
        @RequestBody User user) {
            return authService.register(user);
    }

    @Operation(
        summary = "Fazer login",
        description = "Autentica o usuário no sistema e retorna um token JWT para ser usado em endpoints protegidos."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Login realizado com sucesso"),
        @ApiResponse(responseCode = "401", description = "Usuário ou senha inválidos")
    })
    @PostMapping("/login")
    public Map<String, String> login(
        @Parameter(description = "Credenciais para autenticação (username e password)", required = true)
        @RequestBody User user) {
            return authService.login(user);
    }

    @Operation(
        summary = "Renovar token JWT",
        description = "Permite renovar um token JWT válido antes de ele expirar, retornando um novo token atualizado."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Token renovado com sucesso"),
        @ApiResponse(responseCode = "401", description = "Token inválido ou expirado")
    })
    @PostMapping("/renew-token")
    public Map<String, String> renewToken(
        @Parameter(description = "Token JWT atual no cabeçalho Authorization com o formato 'Bearer {token}'", required = true)
        @RequestHeader("Authorization") String token) {
            return authService.renewToken(token);
    }
}

