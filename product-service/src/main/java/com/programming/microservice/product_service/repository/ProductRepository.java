package com.programming.microservice.product_service.repository;

import com.programming.microservice.product_service.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
  Optional<Product> findByCode(String code);
}
