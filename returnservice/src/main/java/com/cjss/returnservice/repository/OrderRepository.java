package com.cjss.returnservice.repository;

import com.cjss.returnservice.entity.CustomerEntity;
import com.cjss.returnservice.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    OrderEntity findByCustomerEntity(Optional<CustomerEntity> customerEntity);
}
