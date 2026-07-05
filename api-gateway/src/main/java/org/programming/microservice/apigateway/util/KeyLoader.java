package org.programming.microservice.apigateway.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Component
@Slf4j
public class KeyLoader {

  @Value("${jwt.public-key}")
  private  String publicKey;

  public PublicKey loadPublicKey() {
    log.debug(publicKey);
    publicKey=publicKey.replace("-----BEGIN PUBLIC KEY-----", "")
        .replace("-----END PUBLIC KEY-----", "")
        .replaceAll("\\s+", "");
    log.debug("Modified public key {}",publicKey);
    try {
      byte[] keyBytes = Base64.getDecoder().decode(publicKey);
      X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
      return KeyFactory.getInstance("RSA").generatePublic(spec);
    } catch (Exception e) {
      log.error("Error loading public key", e);
      throw new RuntimeException("Error loading public key", e);
    }
  }
}
