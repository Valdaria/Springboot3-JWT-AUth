package com.example.securityjwt.auth;

public record RegisterRequest(
    String firstname,
    String lastname,
    String email,
    String password
) {
}
