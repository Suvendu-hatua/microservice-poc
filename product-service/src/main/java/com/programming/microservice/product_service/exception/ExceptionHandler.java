package com.programming.microservice.product_service.exception;

import com.programming.microservice.product_service.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionHandler {
  @org.springframework.web.bind.annotation.ExceptionHandler(ProductNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotFoundException ex, WebRequest request) {
    ErrorResponse errorResponse = ErrorResponse.builder()
        .status(HttpStatus.NOT_FOUND)
        .message(ex.getMessage())
        .path(request.getDescription(false))
        .timestamp(System.currentTimeMillis())
        .build();
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }
}
