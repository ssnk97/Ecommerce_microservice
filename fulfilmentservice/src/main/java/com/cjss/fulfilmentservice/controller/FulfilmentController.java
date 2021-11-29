package com.cjss.fulfilmentservice.controller;

import com.cjss.fulfilmentservice.service.FulfilmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FulfilmentController {
    @Autowired
    FulfilmentService fulfilmentService;

    @PostMapping("cjss-customer/update-item-status")
    public ResponseEntity<String> updateItemStatus(@RequestParam Long itemId, @RequestParam String status){
        System.out.println(itemId+"  status: "+ status);
        String message = fulfilmentService.updateItemStatus(itemId, status);
        if(message.contains("STATUS UPDATED"))
            return ResponseEntity.status(HttpStatus.OK).body(message);
        if(message.equals("INVALID ID"))
            return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
        return ResponseEntity.status(HttpStatus.CONFLICT).body("UNKNOWN");
    }
}
