package com.cjss.productservice.repository;

import com.cjss.productservice.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<InventoryEntity, Long> {
    InventoryEntity findBySkuCode(String skuCode);
}
