package com.programming.microservice.product_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
  private String name;
  private String code;
  private String description;
  private BigDecimal price;
}
