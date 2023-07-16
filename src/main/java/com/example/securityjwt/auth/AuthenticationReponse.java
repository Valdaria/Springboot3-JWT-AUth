package com.example.securityjwt.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record AuthenticationReponse(String token, String reason) {
  public AuthenticationReponse(String token) {
    this(token, null);
  }

  @JsonIgnore
  public boolean isError() {
    return reason != null && token == null;
  }

}
