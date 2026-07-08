package org.programming.microservices.cartservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.microservices.cartservice.dto.CartRequest;
import org.programming.microservices.cartservice.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {
  private final CartRepository cartRepository;

  public void addToCart(String userName,CartRequest cartRequest){
    log.info("For User {} adding to cart {}",userName,cartRequest);
    cartRepository.save(userName,cartRequest);
  }

  public void getCartItems(String userName){
    log.info("For User {} getting cart items",userName);
    Map<Object, Object> cartItems = cartRepository.getCart(userName);
    System.out.println(cartItems);
  }
}
