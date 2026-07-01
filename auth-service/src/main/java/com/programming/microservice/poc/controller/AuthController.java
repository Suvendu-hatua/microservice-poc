package com.programming.microservice.poc.controller;

import com.programming.microservice.poc.dto.LoginUser;
import com.programming.microservice.poc.dto.RegisterUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

  @PostMapping("/register")
  public ResponseEntity<String> registerUser(@RequestBody RegisterUser registerUser) {

  }

  public ResponseEntity<String> loginUser(@RequestBody LoginUser loginUser) {

  }
}
