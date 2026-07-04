package com.programming.microservice.poc.service;

import com.programming.microservice.poc.util.KeyLoader;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
@AllArgsConstructor
public class JwtService {
  private final KeyLoader keyLoader;

  private Key getSigningKey() {
    return keyLoader.loadPrivateKey();
  }

  public String generateToken(String username){
    return Jwts.builder()
        .subject(username)
        .issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours expiration
        .signWith(getSigningKey())
        .compact();
  }
}
