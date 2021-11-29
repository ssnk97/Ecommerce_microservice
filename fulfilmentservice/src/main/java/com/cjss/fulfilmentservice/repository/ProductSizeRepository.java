package com.cjss.fulfilmentservice.repository;

import com.cjss.fulfilmentservice.entity.ProductSizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSizeRepository extends JpaRepository<ProductSizeEntity, String> {
    ProductSizeEntity findBySkuCode(String skuCode);
}
