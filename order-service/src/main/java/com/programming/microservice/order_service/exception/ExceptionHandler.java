package com.programming.microservice.order_service.exception;

import com.programming.microservice.order_service.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionHandler {
  @org.springframework.web.bind.annotation.ExceptionHandler(ItemNotInStockException.class)
  public ResponseEntity<ErrorResponse> handleItemNotInStockException(ItemNotInStockException ex, WebRequest request) {
    ErrorResponse errorResponse = ErrorResponse.builder()
        .status(HttpStatus.BAD_REQUEST)
        .message(ex.getMessage())
        .path(request.getDescription(false))
        .timestamp(System.currentTimeMillis())
        .build();
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }
}
