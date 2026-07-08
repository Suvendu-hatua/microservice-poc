package org.programming.microservices.cartservice.repository;

import lombok.RequiredArgsConstructor;
import org.programming.microservices.cartservice.dto.CartRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class CartRepository {
  private final RedisTemplate<String,Object> redisTemplate;

  private static final String CART_KEY_PREFIX = "cart:";

  public void save(String userName, CartRequest cartRequest){
    String key = CART_KEY_PREFIX + userName;
    redisTemplate.opsForHash().put(key, cartRequest.getCode(), cartRequest.getQuantity());
    redisTemplate.expire(key, Duration.ofDays(40)); // Set expiration time for the cart
  }

  public Map<Object, Object> getCart(String userName){
    String key = CART_KEY_PREFIX + userName;
    return redisTemplate.opsForHash().entries(key);
  }

  public void deleteItem(String userName, String productCode){
    String key = CART_KEY_PREFIX + userName;
    redisTemplate.opsForHash().delete(key, productCode);
  }

  public void clearCart(String userName){
    String key = CART_KEY_PREFIX + userName;
    redisTemplate.delete(key);
  }

}
