package com.programming.microservice.poc.mapper;

import com.programming.microservice.poc.dto.RegisterUser;
import com.programming.microservice.poc.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MapperService {

  private final PasswordEncoder passwordEncoder;

  public MapperService(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  public User mapToUser(RegisterUser registerUser) {
    return User.builder()
        .username(registerUser.getUsername())
        .password(passwordEncoder.encode(registerUser.getPassword()))
        .email(registerUser.getEmail())
        .firstName(registerUser.getFirstName())
        .lastName(registerUser.getLastName())
        .build();
  }
}
