package com.programming.microservice.poc.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
  private static final String SECRET_KEY = "mysecretkeymysecretkeymysecretkeymysecretkey";

  private Key getSigningKey(){
    byte[] decode = Decoders.BASE64.decode(SECRET_KEY);
    return Keys.hmacShaKeyFor(decode);
  }

  public String generateToken(String username) {
    return Jwts.builder()
        .subject(username)
        .issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours expiration
        .signWith(getSigningKey())
        .compact();
  }
}
