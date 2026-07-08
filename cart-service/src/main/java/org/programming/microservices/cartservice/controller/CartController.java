package org.programming.microservices.cartservice.controller;

import lombok.RequiredArgsConstructor;
import org.programming.microservices.cartservice.dto.CartRequest;
import org.programming.microservices.cartservice.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

  private final CartService cartService;

  @PostMapping("/items")
  public ResponseEntity<String > addToCart(
      @RequestHeader("X-USER-NAME") String userName,
      @RequestBody CartRequest cartRequest){
    cartService.addToCart(userName,cartRequest);
    return ResponseEntity.ok("Item added to cart successfully");
  }

  @GetMapping
  public ResponseEntity<String> getCartItems(
      @RequestHeader("X-USER-NAME") String userName){
    cartService.getCartItems(userName);
    return ResponseEntity.ok("Cart items retrieved successfully");
  }
}
