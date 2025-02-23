//AuthService.java
package com.thinkproject.rest_project.service;

import com.thinkproject.rest_project.dto.LoginResponseDTO;
import com.thinkproject.rest_project.exception.ServiceException;
import com.thinkproject.rest_project.model.User;
import com.thinkproject.rest_project.repository.UserRepository;
import com.thinkproject.rest_project.util.JwtUtil;
import com.thinkproject.rest_project.util.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public String register(User user) {
        validateUser(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "Usuário registrado com sucesso!";
    }

    public LoginResponseDTO login(User user) {
        User existingUser = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new ServiceException(AppConstants.ERROR_INVALID_USER_OR_PASSWORD));

        if (!passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            throw new ServiceException(AppConstants.ERROR_INVALID_USER_OR_PASSWORD);
        }

        String token = jwtUtil.generateToken(existingUser.getUsername());
        return new LoginResponseDTO("Login realizado com sucesso!", token);
    }

    public LoginResponseDTO renewToken(String token) {
        String cleanedToken = extractToken(token);
        validateToken(cleanedToken);

        String username = jwtUtil.extractUsername(cleanedToken);
        String newToken = jwtUtil.generateToken(username);
        return new LoginResponseDTO("Token renovado com sucesso!", newToken);
    }

    private void validateUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new ServiceException(AppConstants.ERROR_USERNAME_EXISTS);
        }
        if (user.getRole() == null || (!user.getRole().equalsIgnoreCase("USER") && !user.getRole().equalsIgnoreCase("ADMIN"))) {
            throw new ServiceException(AppConstants.ERROR_INVALID_ROLE);
        }
        user.setRole(user.getRole().toUpperCase());
    }

    private String extractToken(String token) {
        if (!token.startsWith(AppConstants.BEARER_PREFIX)) {
            throw new ServiceException(AppConstants.ERROR_TOKEN_INVALID);
        }
        return token.substring(AppConstants.BEARER_PREFIX.length());
    }

    private void validateToken(String token) {
        if (!jwtUtil.validateToken(token) || jwtUtil.isTokenExpired(token)) {
            throw new ServiceException(AppConstants.ERROR_TOKEN_INVALID_OR_EXPIRED);
        }
    }
}

