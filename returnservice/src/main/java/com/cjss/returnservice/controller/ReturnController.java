package com.cjss.returnservice.controller;

import com.cjss.returnservice.service.AuthenticationService;
import com.cjss.returnservice.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReturnController {
    @Autowired
    ReturnService returnService;
    @PostMapping("/cjss-customer/return-item/{itemId}")
    public ResponseEntity<String> returnItem(@PathVariable Long itemId, @RequestHeader String encryptedToken){
        AuthenticationService authenticationService = new AuthenticationService();
        if(authenticationService.validToken(encryptedToken)) {
            String message = returnService.returnItem(itemId, authenticationService.getTokenDetails(encryptedToken));
            if(message.contains("RETURN ACCEPTED"))
                return ResponseEntity.status(HttpStatus.OK).body(message);
            if(message.equals("INVALID ITEM ID"))
                return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
            if(message.equals("EMAIL NOT FOUND"))
                return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
            if(message.equals("ALREADY RETURNED"))
                return ResponseEntity.status(HttpStatus.OK).body(message);
            else
                return ResponseEntity.status(HttpStatus.CONFLICT).body("UNKNOWN");

        }
        else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("INVALID TOKEN (CODE 401)");
    }
}
