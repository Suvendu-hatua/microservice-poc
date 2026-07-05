package com.programming.microservice.poc.service;

import com.programming.microservice.poc.util.KeyLoader;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class JwtService {
  private final KeyLoader keyLoader;

  private Key getSigningKey() {
    return keyLoader.loadPrivateKey();
  }

  public String generateToken(Authentication authentication){
    String userName = authentication.getName();
    List<String> roles = authentication.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .toList();
    return Jwts.builder()
        .subject(userName)
        .claim("roles", roles)
        .issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours expiration
        .signWith(getSigningKey())
        .compact();
  }
}
