package com.cjss.inventoryservice.repository;

import com.cjss.inventoryservice.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<InventoryEntity, Long> {
    InventoryEntity findBySkuCode(String skuCode);
}
