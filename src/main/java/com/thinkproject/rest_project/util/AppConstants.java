//AppConstants.java
package com.thinkproject.rest_project.util;

public final class AppConstants {
    private AppConstants() {}

    // Authorization constants
    public static final String BEARER_PREFIX = "Bearer ";
    public static final String ROLE_PREFIX = "ROLE_";

    // Error messages
    public static final String ERROR_USER_NOT_FOUND = "Usuário não encontrado!";
    public static final String ERROR_INVALID_USER_OR_PASSWORD = "Usuário ou senha inválidos!";
    public static final String ERROR_TOKEN_INVALID = "Token inválido!";
    public static final String ERROR_TOKEN_INVALID_OR_EXPIRED = "Token inválido ou expirado!";
    public static final String ERROR_USERNAME_EXISTS = "Username já existe!";
    public static final String ERROR_INVALID_ROLE = "Papel inválido! Use 'USER' ou 'ADMIN'.";

    public static final String ERROR_CLIENT_NOT_FOUND = "Cliente não encontrado com ID: ";
    public static final String ERROR_SERVICE_NOT_FOUND = "Serviço não encontrado com ID: ";
    public static final String ERROR_VENDOR_NOT_FOUND = "Fornecedor não encontrado com ID: ";
}

