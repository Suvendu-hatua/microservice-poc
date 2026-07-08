package com.programming.microservice.order_service.service;

import com.programming.microservice.order_service.dto.InventoryResponse;
import com.programming.microservice.order_service.dto.OrderItemRequest;
import com.programming.microservice.order_service.dto.OrderRequest;
import com.programming.microservice.order_service.exception.ItemNotInStockException;
import com.programming.microservice.order_service.feign.InventoryFeignClient;
import com.programming.microservice.order_service.model.Order;
import com.programming.microservice.order_service.model.OrderItem;
import com.programming.microservice.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
  private final OrderRepository orderRepository;
  private final InventoryFeignClient inventoryFeignClient;

  @Transactional
  public Order placeOrder(OrderRequest orderRequest) {
    List<String> productCodes = orderRequest.getOrderItemRequestList().stream()
        .map(OrderItemRequest::getCode)
        .toList();

    List<InventoryResponse> inventoryResponses = inventoryFeignClient.checkProductInStock(productCodes).getBody();
    boolean allMatch = orderRequest.getOrderItemRequestList().stream()
        .allMatch(orderItemRequest -> {
          InventoryResponse inventoryResponse = findInventoryResponse(Objects.requireNonNull(inventoryResponses), orderItemRequest.getCode());
          return inventoryResponse != null && inventoryResponse.getQuantity() >= orderItemRequest.getQuantity();
        });
    if (!allMatch) {
      throw new ItemNotInStockException("One or more items are not in stock");
    }
    Order order = mapToOrder(orderRequest);
    Order save = orderRepository.save(order);
    log.info("Order saved: {}", order);
    return save;
  }

  private InventoryResponse findInventoryResponse(List<InventoryResponse> inventoryResponses, String code) {
    return inventoryResponses.stream()
        .filter(inventoryResponse -> inventoryResponse.getCode().equals(code))
        .findFirst()
        .orElse(null);
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
