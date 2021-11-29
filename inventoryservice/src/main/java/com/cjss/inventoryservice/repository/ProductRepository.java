package com.cjss.inventoryservice.repository;

import com.cjss.inventoryservice.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {
    ProductEntity findByProductCode(String productCode);
}
