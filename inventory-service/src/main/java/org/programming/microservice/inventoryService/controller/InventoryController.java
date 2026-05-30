package org.programming.microservice.inventoryService.controller;

import lombok.RequiredArgsConstructor;
import org.programming.microservice.inventoryService.dto.InventoryRequest;
import org.programming.microservice.inventoryService.dto.InventoryResponse;
import org.programming.microservice.inventoryService.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
  private final InventoryService inventoryService;

  @GetMapping("/check")
  public ResponseEntity<List<InventoryResponse>> checkProductInStock(@RequestParam List<String> productCode) {
    return ResponseEntity.ok(inventoryService.isInStock(productCode));
  }

  @PostMapping("/add")
  public ResponseEntity<String> addProductsInStock(@RequestBody List<InventoryRequest> inventoryList) {
    inventoryService.addProductsInStock(inventoryList);
    return ResponseEntity.ok("Product added in stock successfully");
  }
}
