package com.programming.microservice.product_service.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "products")
public class Product {
  @Id
  private String id;
  private String name;
  private String code;
  private String description;
  private Double price;
}
