package com.cjss.coordinatorservice.controller;

import com.cjss.coordinatorservice.resttemplate.ReturnRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReturnRestController {
    @Autowired
    ReturnRestTemplate returnRestTemplate;
    @PostMapping("/coordinator/return-item/{itemId}")
    public ResponseEntity<String> returnItem(@PathVariable Long itemId, @RequestHeader String encryptedToken) {
        try{
           return returnRestTemplate.returnItem(itemId, encryptedToken);
        }catch(Exception e){
            System.out.println("COORDINATOR RETURN CATCH :: "+e);
            if(e.getMessage().contains("401"))
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED");
            else if(e.getMessage().equals("INVALID ITEM ID"))
                return ResponseEntity.status(HttpStatus.CONFLICT).body("INVALID ITEM ID");
            else if(e.getMessage().equals("EMAIL NOT FOUND"))
                return ResponseEntity.status(HttpStatus.CONFLICT).body("INVALID ITEM ID");
            else
                return ResponseEntity.status(HttpStatus.CONFLICT).body("UNKNOWN");
        }
    }
}
