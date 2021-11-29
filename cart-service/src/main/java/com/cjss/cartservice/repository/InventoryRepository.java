package com.cjss.cartservice.repository;

import com.cjss.cartservice.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<InventoryEntity, Long> {
    InventoryEntity findBySkuCode(String skuCode);
}
