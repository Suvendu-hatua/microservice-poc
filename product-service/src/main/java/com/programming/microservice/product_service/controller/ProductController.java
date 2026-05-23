package com.programming.microservice.product_service.controller;

import com.programming.microservice.product_service.dto.ProductRequest;
import com.programming.microservice.product_service.dto.ProductResponse;
import com.programming.microservice.product_service.model.Product;
import com.programming.microservice.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/product")
@RestController
@RequiredArgsConstructor
public class ProductController {
  private final ProductService productService;

  @PostMapping
  public ResponseEntity<String> saveProduct(@RequestBody ProductRequest product) {
    Product savedProduct = productService.saveProduct(product);
    return ResponseEntity.status(HttpStatus.CREATED).body(
        String.format("""
        status: %s
        message: Product saved successfully with id %s
        """,
            HttpStatus.CREATED,
            savedProduct.getId()
        )
    );
  }

  @GetMapping
  public ResponseEntity<ProductResponse> getProductById(@RequestParam("id") String id) {
    ProductResponse productById = productService.getProductById(id);
    return ResponseEntity.status(HttpStatus.OK).body(productById);
  }
}
