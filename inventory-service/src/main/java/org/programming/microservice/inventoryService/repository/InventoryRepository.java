package org.programming.microservice.inventoryService.repository;

import org.programming.microservice.inventoryService.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
