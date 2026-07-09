package org.programming.microservices.cartservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.microservices.cartservice.dto.Cart;
import org.programming.microservices.cartservice.dto.CartItem;
import org.programming.microservices.cartservice.dto.CartRequest;
import org.programming.microservices.cartservice.feign.ProductFeignClient;
import org.programming.microservices.cartservice.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {
  private final CartRepository cartRepository;
  private final ProductFeignClient productFeignClient;

  public void addToCart(String userName, CartRequest cartRequest) {
    log.info("For User {} adding to cart {}", userName, cartRequest);
    cartRepository.save(userName, cartRequest);
  }

  public Cart getCartItems(String userName) {
    log.info("For User {} getting cart items", userName);
    Map<Object, Object> cartItems = cartRepository.getCart(userName);
    List<CartItem> availableItems = new ArrayList<>();

    cartItems.forEach((key, value) -> {
      String productCode = (String) key;
      Integer quantity = (Integer) value;
      var productResponse = productFeignClient.getProductByProductCode(productCode).getBody();
      if (productResponse != null) {
        availableItems.add(CartItem.builder()
            .name(productResponse.getName())
            .code(productResponse.getCode())
            .price(productResponse.getPrice())
            .quantity(quantity)
            .build());
      }
    });
    return Cart.builder()
        .items(availableItems)
        .build();
  }

  public Cart removeFromCart(String userName, String productCode) {
    cartRepository.deleteItem(userName, productCode);
    return getCartItems(userName);
  }

  public Cart updateCart(String userName, CartRequest cartRequest) {
    cartRepository.save(userName, cartRequest);
    return getCartItems(userName);
  }

  public void clearCart(String userName) {
    cartRepository.clearCart(userName);
  }
}
