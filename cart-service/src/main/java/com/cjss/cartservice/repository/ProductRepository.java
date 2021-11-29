package com.cjss.cartservice.repository;

import com.cjss.cartservice.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {
    ProductEntity findByProductCode(String productCode);
}
