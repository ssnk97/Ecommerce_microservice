package com.cjss.fulfilmentservice.repository;

import com.cjss.fulfilmentservice.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
}
