package com.cjss.cartservice.repository;

import com.cjss.cartservice.entity.ItemsOrderedEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsOrderedRepository extends JpaRepository<ItemsOrderedEntity, Long> {
}
