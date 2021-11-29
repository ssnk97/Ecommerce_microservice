package com.cjss.cartservice.repository;

import com.cjss.cartservice.entity.CartEntity;
import com.cjss.cartservice.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<CartEntity, Long> {
    List<CartEntity> findByCustomerEntity(CustomerEntity customerEntity);
}
