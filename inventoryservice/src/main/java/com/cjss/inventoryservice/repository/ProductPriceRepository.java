package com.cjss.inventoryservice.repository;

import com.cjss.inventoryservice.entity.ProductPriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductPriceRepository extends JpaRepository<ProductPriceEntity, String> {

    ProductPriceEntity findBySkuCode(String skuCode);
}
