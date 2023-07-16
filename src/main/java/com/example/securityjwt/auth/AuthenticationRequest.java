package com.example.securityjwt.auth;

public record AuthenticationRequest(
    String email,
    String password
) {
}
