package com.cjss.fulfilmentservice.repository;

import com.cjss.fulfilmentservice.entity.ProductPriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductPriceRepository extends JpaRepository<ProductPriceEntity, String> {

    ProductPriceEntity findBySkuCode(String skuCode);
}
