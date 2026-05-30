package com.programming.microservice.order_service.exception;

public class ItemNotInStockException extends RuntimeException{
  public ItemNotInStockException(String message) {
    super(message);
  }
}
