//AuthController.java
package com.thinkproject.rest_project.controller;

import com.thinkproject.rest_project.model.User;
import com.thinkproject.rest_project.dto.request.CreateUserRequest;
import com.thinkproject.rest_project.dto.request.LoginRequest;
import com.thinkproject.rest_project.service.AuthService;
import com.thinkproject.rest_project.util.UserMapper;
import com.thinkproject.rest_project.config.documentation.AuthControllerApiOperation.Register;
import com.thinkproject.rest_project.config.documentation.AuthControllerApiOperation.Login;
import com.thinkproject.rest_project.config.documentation.AuthControllerApiOperation.RenewToken;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Register
    @PostMapping("/register")
    public Map<String, String> register(@Valid @RequestBody CreateUserRequest request) {
        User user = UserMapper.mapCreateUserRequestToUser(request);
        return authService.register(user);
    }

    @Login
    @PostMapping("/login")
    public Map<String, String> login(@Valid @RequestBody LoginRequest request) {
        User user = UserMapper.mapLoginRequestToUser(request);
        return authService.login(user);
    }

    @RenewToken
    @PostMapping("/renew-token")
    public Map<String, String> renewToken(@RequestHeader("Authorization") String token) {
        return authService.renewToken(token);
    }
}

