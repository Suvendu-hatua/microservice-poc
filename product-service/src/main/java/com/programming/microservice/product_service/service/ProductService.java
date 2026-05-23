package com.programming.microservice.product_service.service;

import com.programming.microservice.product_service.dto.ProductRequest;
import com.programming.microservice.product_service.dto.ProductResponse;
import com.programming.microservice.product_service.exception.ProductNotFoundException;
import com.programming.microservice.product_service.mapper.ProductMapper;
import com.programming.microservice.product_service.model.Product;
import com.programming.microservice.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
  private final ProductRepository productRepository;
  private final ProductMapper mapper;

  @Transactional
  public Product saveProduct(ProductRequest product) {
    Product prod = mapper.mapToProduct(product);
    Product savedProduct = productRepository.save(prod);
    if(savedProduct.getId()!=null) {
      log.info("Product saved successfully with id {}", savedProduct.getId());
    }
    return savedProduct;
  }

  public List<ProductResponse> getAllProducts() {
    List<Product> allProducts = productRepository.findAll();
    return allProducts.stream().map(mapper::mapToProductResponse).toList();
  }

  public ProductResponse getProductById(String id) {
    Product product = productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException("Product not found with id " + id+"!"));
    return mapper.mapToProductResponse(product);
  }

  public ProductResponse getProductByCode(String code) {
    Optional<Product> optionalProduct = productRepository.findByCode(code);
    if(optionalProduct.isEmpty()) {
      throw new ProductNotFoundException("Product not found with code " + code);
    }else{
      return mapper.mapToProductResponse(optionalProduct.get());
    }
  }
}
