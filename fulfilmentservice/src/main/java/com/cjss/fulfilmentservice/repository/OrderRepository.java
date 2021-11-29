package com.cjss.fulfilmentservice.repository;

import com.cjss.fulfilmentservice.entity.CustomerEntity;
import com.cjss.fulfilmentservice.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    OrderEntity findByCustomerEntity(Optional<CustomerEntity> customerEntity);
}
