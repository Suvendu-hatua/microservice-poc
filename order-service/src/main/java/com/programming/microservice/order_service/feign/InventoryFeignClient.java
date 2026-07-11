package com.programming.microservice.order_service.feign;


import com.programming.microservice.order_service.dto.InventoryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "inventory-service",fallbackFactory = InventoryFeignClientFallbackFactory.class)
public interface InventoryFeignClient {
  @GetMapping("/api/inventory/check")
  ResponseEntity<List<InventoryResponse>> checkProductInStock(@RequestParam List<String> productCode);
}
