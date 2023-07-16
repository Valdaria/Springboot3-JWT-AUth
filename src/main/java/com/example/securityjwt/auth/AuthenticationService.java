package com.example.securityjwt.auth;

import com.example.securityjwt.config.JwtService;
import com.example.securityjwt.user.Role;
import com.example.securityjwt.user.User;
import com.example.securityjwt.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationReponse register(RegisterRequest request) {

    User user = User.builder()
        .firstname(request.firstname())
        .lastname(request.lastname())
        .email(request.email())
        .password(passwordEncoder.encode(request.password()))
        .role(Role.USER)
        .build();
    if(userRepository.existsByEmail(request.email())) {
      return new AuthenticationReponse(null, "Registration failed");
    }
    userRepository.save(user);
    String jwtToken = jwtService.generateToken(user);
    return new AuthenticationReponse(jwtToken);
  }

  public AuthenticationReponse authenticate(AuthenticationRequest request) {

    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.email(), request.password())
    );

    User user = userRepository.findByEmail(request.email()).orElseThrow();
    String jwtToken = jwtService.generateToken(user);
    return new AuthenticationReponse(jwtToken);
  }
}
