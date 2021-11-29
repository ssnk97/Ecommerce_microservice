package com.cjss.inventoryservice.controller;

import com.cjss.inventoryservice.entity.InventoryEntity;
import com.cjss.inventoryservice.entity.ProductPriceEntity;
import com.cjss.inventoryservice.entity.ProductSizeEntity;
import com.cjss.inventoryservice.model.InventoryModel;
import com.cjss.inventoryservice.repository.ProductPriceRepository;
import com.cjss.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class InventoryController {
    @Autowired
    InventoryService inventoryService;
    @Autowired
    ProductPriceRepository productPriceRepository;

    @PostMapping("/cjss-customer/add-inventory")
    public ResponseEntity<String> addInventory(@Valid @RequestBody InventoryModel inventoryModel, @RequestParam String skuCode){
        String message = inventoryService.addInventory(inventoryModel, skuCode);
        if(message.equals("INVENTORY ADDED"))
            return ResponseEntity.status(HttpStatus.CREATED).body("INVENTORY ADDED");
        else if(message.equals("SKUCODE INVALID"))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("SKUCODE INVALID");
        else if(message.equals("ALREADY SKU AVAILABLE"))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("ALREADY SKU AVAILABLE");
        else  return ResponseEntity.status(HttpStatus.CONFLICT).body("UNKNOWN");
    }

}

