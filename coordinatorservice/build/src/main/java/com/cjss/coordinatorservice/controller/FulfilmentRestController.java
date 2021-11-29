package com.cjss.coordinatorservice.controller;

import com.cjss.coordinatorservice.resttemplate.FulfilmentRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FulfilmentRestController {
    @Autowired
    FulfilmentRestTemplate fulfilmentRestTemplate;

    @PostMapping("coordinator/update-item-status")
    public ResponseEntity<String> updateItemStatus(@RequestParam Long itemId, @RequestParam String status){
        try{
            return fulfilmentRestTemplate.updateItemStatus(itemId, status);
        }
        catch(Exception e){
            if(e.getMessage().contains("STATUS UPDATED"))
                return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
            if(e.getMessage().equals("INVALID ID"))
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("UNKNOWN");
        }
    }
}
