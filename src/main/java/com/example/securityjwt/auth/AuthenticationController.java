package com.example.securityjwt.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationReponse> register(
      @RequestBody RegisterRequest request
  ) {
      AuthenticationReponse response = service.register(request);
      if (response.isError()) {
        return ResponseEntity.status(403).body(response);
      }
       return ResponseEntity.ok(response);

  }

  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationReponse> register(
      @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(service.authenticate(request));
  }

}
