package com.cjss.coordinatorservice.controller;

import com.cjss.coordinatorservice.model.InventoryModel;
import com.cjss.coordinatorservice.resttemplate.InventoryRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class InventoryRestController {
    @Autowired
    InventoryRestTemplate inventoryRestTemplate;
    @PostMapping("/coordinator/add-inventory")
    public ResponseEntity<String> addInventory(@Valid @RequestBody InventoryModel inventoryModel, @RequestParam String skuCode) {
        try{
            return inventoryRestTemplate.addInventory(inventoryModel, skuCode);
        }
        catch(Exception e){
            System.out.println("INVENTORY CATCH: "+ e);
            if(e.getMessage().contains("SKUCODE INVALID"))
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            else if (e.getMessage().contains("ALREADY SKU AVAILABLE"))
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            else return ResponseEntity.status(HttpStatus.CONFLICT).body("UNKNOWN");
        }
    }
}
