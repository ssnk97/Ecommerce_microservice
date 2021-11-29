package com.cjss.fulfilmentservice.repository;

import com.cjss.fulfilmentservice.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {
    CustomerEntity findByEmail(String email);
}
