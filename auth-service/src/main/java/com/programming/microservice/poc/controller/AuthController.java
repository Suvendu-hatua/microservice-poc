package com.programming.microservice.poc.controller;

import com.programming.microservice.poc.dto.LoginUser;
import com.programming.microservice.poc.dto.RegisterUser;
import com.programming.microservice.poc.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {

  private final UserService userService;

  @PostMapping("/register")
  public ResponseEntity<String> registerUser( @RequestBody @Valid RegisterUser registerUser) {
    userService.registerUser(registerUser);
    return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
  }

  @PostMapping("/login")
  public ResponseEntity<String> loginUser(@RequestBody LoginUser loginUser) {
   String jwtToken= userService.loginUser(loginUser);
   return ResponseEntity.ok(jwtToken);
  }
}
