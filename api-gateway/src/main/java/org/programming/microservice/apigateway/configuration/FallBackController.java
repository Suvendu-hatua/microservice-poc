package org.programming.microservice.apigateway.configuration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallBackController {
  @GetMapping("/auth")
  public ResponseEntity<String> authFallback() {
    return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
        .body("Auth Service is currently unavailable.");
  }

  @GetMapping("/order")
  public ResponseEntity<String> orderFallback() {
    return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
        .body("Order Service is currently unavailable.");
  }

  @GetMapping("/product")
  public ResponseEntity<String> productFallback() {
    return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
        .body("Product Service is currently unavailable.");
  }

  @GetMapping("/inventory")
  public ResponseEntity<String> inventoryFallback() {
    return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
        .body("Inventory Service is currently unavailable.");
  }

  @GetMapping("/cart")
  public ResponseEntity<String> cartFallback() {
    return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
        .body("Cart Service is currently unavailable.");
  }
}
