package com.programming.microservice.poc.repository;

import com.programming.microservice.poc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
