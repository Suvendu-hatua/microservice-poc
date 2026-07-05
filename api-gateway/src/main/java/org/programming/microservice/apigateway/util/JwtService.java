package org.programming.microservice.apigateway.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.PublicKey;

@Component
@RequiredArgsConstructor
public class JwtService {
  private final KeyLoader keyLoader;

  public Claims validateToken(String token) {
    PublicKey publicKey = keyLoader.loadPublicKey();
    return Jwts.parser()
        .verifyWith(publicKey)
        .build()
        .parseSignedClaims(token)
        .getPayload();
  }
}
