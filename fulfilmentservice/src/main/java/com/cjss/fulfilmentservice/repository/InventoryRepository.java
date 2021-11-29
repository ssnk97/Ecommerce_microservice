package com.cjss.fulfilmentservice.repository;

import com.cjss.fulfilmentservice.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<InventoryEntity, Long> {
    InventoryEntity findBySkuCode(String skuCode);
}
