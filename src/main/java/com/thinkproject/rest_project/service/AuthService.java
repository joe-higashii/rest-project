//AuthService.java
package com.thinkproject.rest_project.service;

import com.thinkproject.rest_project.exception.BadRequestException;
import com.thinkproject.rest_project.model.User;
import com.thinkproject.rest_project.repository.UserRepository;
import com.thinkproject.rest_project.util.JwtUtil;
import com.thinkproject.rest_project.util.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public Map<String, String> register(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new BadRequestException(AppConstants.ERROR_USERNAME_EXISTS);
        }

        String role = user.getRole();
        if (role == null || (!role.equalsIgnoreCase("USER") && !role.equalsIgnoreCase("ADMIN"))) {
            throw new BadRequestException(AppConstants.ERROR_INVALID_ROLE);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(role.toUpperCase());
        userRepository.save(user);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Usuário registrado com sucesso!");
        return response;
    }

    public Map<String, String> login(User user) {
        User existingUser = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new BadRequestException(AppConstants.ERROR_INVALID_USER_OR_PASSWORD));

        if (!passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            throw new BadRequestException(AppConstants.ERROR_INVALID_USER_OR_PASSWORD);
        }

        String token = jwtUtil.generateToken(existingUser.getUsername());

        Map<String, String> response = new HashMap<>();
        response.put("message", "Login realizado com sucesso!");
        response.put("token", token);
        return response;
    }

    public Map<String, String> renewToken(String token) {
        if (!token.startsWith(AppConstants.BEARER_PREFIX)) {
            throw new BadRequestException(AppConstants.ERROR_TOKEN_INVALID);
        }

        token = token.substring(AppConstants.BEARER_PREFIX.length());

        if (!jwtUtil.validateToken(token) || jwtUtil.isTokenExpired(token)) {
            throw new BadRequestException(AppConstants.ERROR_TOKEN_INVALID_OR_EXPIRED);
        }

        String username = jwtUtil.extractUsername(token);
        String newToken = jwtUtil.generateToken(username);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Token renovado com sucesso!");
        response.put("token", newToken);
        return response;
    }
}

