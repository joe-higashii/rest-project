//AuthController.java
package com.thinkproject.rest_project.controller;

import com.thinkproject.rest_project.model.User;
import com.thinkproject.rest_project.repository.UserRepository;
import com.thinkproject.rest_project.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

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
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username já existe!");
        }

        String role = user.getRole();
        if (role == null || (!role.equalsIgnoreCase("USER") && !role.equalsIgnoreCase("ADMIN"))) {
            throw new RuntimeException("Papel inválido! Use 'USER' ou 'ADMIN'.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(role.toUpperCase());
        userRepository.save(user);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Usuário registrado com sucesso!");
        return response;
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
        User existingUser = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuário ou senha inválidos!"));

        if (!passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            throw new RuntimeException("Usuário ou senha inválidos!");
        }

        String token = jwtUtil.generateToken(existingUser.getUsername());

        Map<String, String> response = new HashMap<>();
        response.put("message", "Login realizado com sucesso!");
        response.put("token", token);
        return response;
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
        if (!token.startsWith("Bearer ")) {
            throw new RuntimeException("Token inválido!");
        }

        token = token.substring(7);

        if (!jwtUtil.validateToken(token) || jwtUtil.isTokenExpired(token)) {
            throw new RuntimeException("Token inválido ou expirado!");
        }

        String username = jwtUtil.extractUsername(token);
        String newToken = jwtUtil.generateToken(username);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Token renovado com sucesso!");
        response.put("token", newToken);
        return response;
    }
}

