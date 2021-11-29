package com.cjss.inventoryservice.repository;

import com.cjss.inventoryservice.entity.ProductPriceEntity;
import com.cjss.inventoryservice.entity.ProductSizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSizeRepository extends JpaRepository<ProductSizeEntity, String> {
    ProductSizeEntity findBySkuCode(String skuCode);
}
