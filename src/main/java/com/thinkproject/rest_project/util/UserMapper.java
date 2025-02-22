//UserMapper.java
package com.thinkproject.rest_project.util;

import com.thinkproject.rest_project.dto.request.CreateUserRequest;
import com.thinkproject.rest_project.dto.request.LoginRequest;
import com.thinkproject.rest_project.model.User;

public class UserMapper {

    public static User mapCreateUserRequestToUser(CreateUserRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());
        return user;
    }

    public static User mapLoginRequestToUser(LoginRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        return user;
    }
}

