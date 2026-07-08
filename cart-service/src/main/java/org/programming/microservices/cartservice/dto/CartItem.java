package org.programming.microservices.cartservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
  private String name;
  private String code;
  private BigDecimal price;
  private int quantity;
}
