package com.cjss.cartservice.repository;

import com.cjss.cartservice.entity.ProductPriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductPriceRepository extends JpaRepository<ProductPriceEntity, String> {

    ProductPriceEntity findBySkuCode(String skuCode);
}
