package com.cjss.productservice.repository;

import com.cjss.productservice.entity.ProductSizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSizeRepository extends JpaRepository<ProductSizeEntity, String> {
    ProductSizeEntity findBySkuCode(String skuCode);
}
