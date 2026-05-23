package com.programming.microservice.product_service.repository;

import com.programming.microservice.product_service.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
  List<Product> findByCode(String code);
}
