package com.programming.microservice.order_service.service;

import com.programming.microservice.order_service.dto.OrderItemRequest;
import com.programming.microservice.order_service.dto.OrderRequest;
import com.programming.microservice.order_service.model.Order;
import com.programming.microservice.order_service.model.OrderItem;
import com.programming.microservice.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
  private final OrderRepository orderRepository;

  @Transactional
  public Order placeOrder(OrderRequest orderRequest) {
    Order order = mapToOrder(orderRequest);
    Order save = orderRepository.save(order);
    log.info("Order saved: {}", order);
    return save;
  }

  private Order mapToOrder(OrderRequest orderRequest) {
    List<OrderItem> orderItemList = orderRequest.getOrderItemRequestList().stream()
        .map(this::mapToOrderItem)
        .toList();
    return Order.builder()
        .orderNumber(UUID.randomUUID().toString())
        .orderItemList(orderItemList)
        .build();
  }
  private OrderItem mapToOrderItem(OrderItemRequest orderItemRequest) {
    return OrderItem.builder()
        .code(orderItemRequest.getCode())
        .price(orderItemRequest.getPrice())
        .quantity(orderItemRequest.getQuantity())
        .build();
  }
}
