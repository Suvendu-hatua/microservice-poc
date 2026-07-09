package org.programming.microservices.cartservice.controller;

import lombok.RequiredArgsConstructor;
import org.programming.microservices.cartservice.dto.Cart;
import org.programming.microservices.cartservice.dto.CartRequest;
import org.programming.microservices.cartservice.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart/items")
@RequiredArgsConstructor
public class CartController {

  private static final String USER_NAME_HEADER = "X-USER-NAME";
  private final CartService cartService;

  @PostMapping()
  public ResponseEntity<String > addToCart(
      @RequestHeader(USER_NAME_HEADER) String userName,
      @RequestBody CartRequest cartRequest){
    cartService.addToCart(userName,cartRequest);
    return ResponseEntity.ok("Item added to cart successfully");
  }

  @GetMapping()
  public ResponseEntity<Cart> getCartItems(
      @RequestHeader(USER_NAME_HEADER) String userName){
    Cart cart = cartService.getCartItems(userName);
    return ResponseEntity.ok(cart);
  }

  @DeleteMapping("/{productCode}")
  public ResponseEntity<Cart> removeFromCart(
      @RequestHeader(USER_NAME_HEADER) String userName,
      @PathVariable String productCode){
    Cart cart = cartService.removeFromCart(userName, productCode);
    return ResponseEntity.ok(cart);
  }

  @PutMapping()
  public ResponseEntity<Cart> updateCart(
      @RequestHeader(USER_NAME_HEADER) String userName,
      @RequestBody CartRequest cartRequest){
    Cart cart = cartService.updateCart(userName, cartRequest);
    return ResponseEntity.ok(cart);
  }

  @DeleteMapping
  public ResponseEntity<String> clearCart(
      @RequestHeader(USER_NAME_HEADER) String userName){
    cartService.clearCart(userName);
    return ResponseEntity.ok("Cart cleared successfully");
  }
}
