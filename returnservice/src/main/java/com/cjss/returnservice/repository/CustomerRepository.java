package com.cjss.returnservice.repository;

import com.cjss.returnservice.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {
    CustomerEntity findByEmail(String email);
}
