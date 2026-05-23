package com.programming.microservice.product_service.mapper;

import com.programming.microservice.product_service.dto.ProductRequest;
import com.programming.microservice.product_service.dto.ProductResponse;
import com.programming.microservice.product_service.model.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
  public Product mapToProduct(ProductRequest product) {
    return Product.builder()
        .name(product.getName())
        .code(product.getCode())
        .description(product.getDescription())
        .price(product.getPrice())
        .build();
  }
  public ProductResponse mapToProductResponse(Product product) {
    return ProductResponse.builder()
        .id(product.getId())
        .name(product.getName())
        .code(product.getCode())
        .description(product.getDescription())
        .price(product.getPrice())
        .build();
  }
}
