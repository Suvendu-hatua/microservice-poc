package org.programming.microservices.cartservice.feign;

import org.programming.microservices.cartservice.dto.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "product-service")
public interface ProductFeignClient {
  @GetMapping("/api/v1/product/search")
  ResponseEntity<ProductResponse> getProductByProductCode(@RequestParam("product-code") String productCode);
}
