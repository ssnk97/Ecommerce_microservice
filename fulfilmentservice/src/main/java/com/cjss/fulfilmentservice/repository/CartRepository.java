package com.cjss.fulfilmentservice.repository;

import com.cjss.fulfilmentservice.entity.CartEntity;
import com.cjss.fulfilmentservice.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<CartEntity, Long> {
    List<CartEntity> findByCustomerEntity(CustomerEntity customerEntity);
}
