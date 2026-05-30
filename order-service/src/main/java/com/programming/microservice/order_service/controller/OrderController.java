package com.programming.microservice.order_service.controller;

import com.programming.microservice.order_service.dto.OrderRequest;
import com.programming.microservice.order_service.model.Order;
import com.programming.microservice.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
  private final OrderService orderService;

  @PostMapping
  public ResponseEntity<String> placeOrder(@RequestBody OrderRequest orderRequest) {
    Order order = orderService.placeOrder(orderRequest);
    return ResponseEntity.ok("Order placed successfully with orderNumber: " + order.getOrderNumber());
  }
}
