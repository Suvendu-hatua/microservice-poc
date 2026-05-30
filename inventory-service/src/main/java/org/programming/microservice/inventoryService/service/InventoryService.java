package org.programming.microservice.inventoryService.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.microservice.inventoryService.dto.InventoryResponse;
import org.programming.microservice.inventoryService.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class InventoryService {
  private final InventoryRepository inventoryRepository;

  public List<InventoryResponse> isInStock(List<String> productCode) {
    return inventoryRepository.findAll()
      .stream()
      .filter(inventory -> productCode.contains(inventory.getCode()))
      .map(inventory -> InventoryResponse.builder()
        .code(inventory.getCode())
        .quantity(inventory.getQuantity())
        .build())
      .toList();
  }
}
