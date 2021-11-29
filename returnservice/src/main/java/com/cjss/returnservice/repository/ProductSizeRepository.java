package com.cjss.returnservice.repository;

import com.cjss.returnservice.entity.ProductSizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSizeRepository extends JpaRepository<ProductSizeEntity, String> {
    ProductSizeEntity findBySkuCode(String skuCode);
}
