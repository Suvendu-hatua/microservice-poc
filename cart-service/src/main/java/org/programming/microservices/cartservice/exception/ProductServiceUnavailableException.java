package org.programming.microservices.cartservice.exception;

public class ProductServiceUnavailableException extends RuntimeException{
  public ProductServiceUnavailableException(String message, Throwable cause) {
    super(message,cause);
  }
}
