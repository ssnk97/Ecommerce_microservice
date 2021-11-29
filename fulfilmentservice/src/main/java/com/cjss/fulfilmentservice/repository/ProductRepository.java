package com.cjss.fulfilmentservice.repository;

import com.cjss.fulfilmentservice.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {
    ProductEntity findByProductCode(String productCode);
}
