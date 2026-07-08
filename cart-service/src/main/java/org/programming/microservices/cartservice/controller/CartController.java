package org.programming.microservices.cartservice.controller;

import org.programming.microservices.cartservice.dto.CartRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

  @PostMapping("/items")
  public ResponseEntity<String > addToCart(@RequestBody CartRequest cartRequest){

  }
}
