package com.thinkproject.rest_project.controller;

import com.thinkproject.rest_project.model.User;
import com.thinkproject.rest_project.repository.UserRepository;
import com.thinkproject.rest_project.util.JwtUtil;
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

    @PostMapping("/register")
    public Map<String, String> register(@RequestBody User user) {
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

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody User user) {
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

    @PostMapping("/renew-token")
    public Map<String, String> renewToken(@RequestHeader("Authorization") String token) {
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
