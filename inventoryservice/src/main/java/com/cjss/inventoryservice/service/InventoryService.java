package com.cjss.inventoryservice.service;

import com.cjss.inventoryservice.entity.InventoryEntity;
import com.cjss.inventoryservice.entity.ProductSizeEntity;
import com.cjss.inventoryservice.model.InventoryModel;
import com.cjss.inventoryservice.repository.InventoryRepository;
import com.cjss.inventoryservice.repository.ProductSizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Optional;
@Service
public class InventoryService {
    @Autowired
    InventoryRepository inventoryRepository;
    @Autowired
    ProductSizeRepository productSizeRepository;

    public String addInventory(InventoryModel inventoryModel, String skuCode){
        Optional<ProductSizeEntity> productSizeEntity = Optional.ofNullable(productSizeRepository.findBySkuCode(skuCode));
        Optional<InventoryEntity> inventory = Optional.ofNullable(inventoryRepository.findBySkuCode(skuCode));
        if(inventory.isPresent()){
            return "ALREADY SKU AVAILABLE";
        }
        else if(productSizeEntity.isPresent()){
            InventoryEntity inventoryEntity = new InventoryEntity();
            inventoryEntity.setQuantityAvailable(inventoryModel.getQuantityAvailable());
            inventoryEntity.setSkuCode(skuCode);
            productSizeEntity.get().setInventoryEntity(inventoryEntity);
            productSizeRepository.save(productSizeEntity.get());
            return "INVENTORY ADDED";
        }
        else return "SKUCODE INVALID";

    }
}
