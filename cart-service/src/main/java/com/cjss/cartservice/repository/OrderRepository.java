package com.cjss.cartservice.repository;

import com.cjss.cartservice.entity.CustomerEntity;
import com.cjss.cartservice.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    OrderEntity findByCustomerEntity(Optional<CustomerEntity> customerEntity);
}
