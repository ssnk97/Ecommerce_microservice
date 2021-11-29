package com.cjss.productservice.repository;

import com.cjss.productservice.entity.ProductPriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductPriceRepository extends JpaRepository<ProductPriceEntity, String> {

    ProductPriceEntity findBySkuCode(String skuCode);
}
