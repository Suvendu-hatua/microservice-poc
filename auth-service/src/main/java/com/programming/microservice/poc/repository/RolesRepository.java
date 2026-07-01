package com.programming.microservice.poc.repository;

import com.programming.microservice.poc.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Role, Long> {
}
