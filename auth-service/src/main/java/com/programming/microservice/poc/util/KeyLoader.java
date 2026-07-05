package com.programming.microservice.poc.util;

import io.jsonwebtoken.io.Decoders;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

@Component
@Slf4j
public class KeyLoader {
  @Value("${jwt.private-key}")
  private  String secretKey;

  public PrivateKey loadPrivateKey() {
    log.debug(secretKey);
    secretKey=secretKey.replace("-----BEGIN PRIVATE KEY-----","")
        .replace("-----END PRIVATE KEY-----","")
        .replaceAll("\\s","");
    log.debug("Modified Secret Key {}",secretKey);
    byte[] decode = Decoders.BASE64.decode(secretKey);
    PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(decode);
    try {
      return KeyFactory.getInstance("RSA")
          .generatePrivate(pkcs8EncodedKeySpec);
    } catch (InvalidKeySpecException |NoSuchAlgorithmException e) {
      log.error(e.getMessage());
      return null;
    }
  }
}
