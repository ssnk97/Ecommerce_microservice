package com.cjss.cartservice.repository;

import com.cjss.cartservice.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {
    CustomerEntity findByEmail(String email);
}
