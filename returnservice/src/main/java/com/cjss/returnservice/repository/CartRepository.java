package com.cjss.returnservice.repository;

import com.cjss.returnservice.entity.CartEntity;
import com.cjss.returnservice.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<CartEntity, Long> {
    List<CartEntity> findByCustomerEntity(CustomerEntity customerEntity);
}
