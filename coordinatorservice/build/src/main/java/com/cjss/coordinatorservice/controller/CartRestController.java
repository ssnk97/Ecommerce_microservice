package com.cjss.coordinatorservice.controller;

import com.cjss.coordinatorservice.model.CartModel;
import com.cjss.coordinatorservice.resttemplate.CartRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;

@RestController
public class CartRestController {
    @Autowired
    CartRestTemplate cartRestTemplate;

    @PostMapping("/coordinator/add-cart")
    public ResponseEntity<String> addToCart(@Valid @RequestBody CartModel cartModel, @RequestHeader String encryptedToken) {
        try{
            return cartRestTemplate.addToCart(cartModel, encryptedToken);
        }
        catch(Exception e){
            System.out.println("COOR:: ADD-CART: "+e);

            // HttpStatus httpStatus = ((HttpClientErrorException.BadRequest) e).getStatusCode();
            //System.out.println(httpStatus);
            if(e.getMessage().contains("TOKEN IS EMPTY"))
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
            if(e.getMessage().contains("LIMITED STOCK"))
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
            if(e.getMessage().contains("401"))
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED");
            else return ResponseEntity.status(HttpStatus.CONFLICT).body("UNKNOWN");
        }
    }

    @GetMapping("/coordinator/view-cart")
    public ResponseEntity<String> viewCart(@RequestHeader String encryptedToken){
        try{
            return cartRestTemplate.viewCart(encryptedToken);
        }
        catch(Exception e){
            System.out.println("VIEW CART:: CATCH: "+e);
            if(e.getMessage().contains("401"))
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED");
            else return ResponseEntity.status(HttpStatus.CONFLICT).body("UNKNOWN");
        }
    }


    @PostMapping("/coordinator/place-order")
    public ResponseEntity<String> placeOrder(@RequestParam Long addressId, @RequestHeader String encryptedToken){
       try{
           return cartRestTemplate.placeOrder(addressId, encryptedToken);
       }
       catch(Exception e){
           System.out.println("COOR:: place-ORDER: "+e);

           //HttpStatus httpStatus = ((HttpClientErrorException.BadRequest) e).getStatusCode();
           if(e.getMessage().contains("ADDRESS ID MISMATCH"))
               return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
           else if(e.getMessage().contains("EMPTY CART"))
               return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
           else if(e.getMessage().contains("401")) {
               return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED!..PLEASE LOGIN");
           }
           else return ResponseEntity.status(HttpStatus.CONFLICT).body("UNKNOWN");
       }
    }


    @GetMapping("/coordinator/get-item-status/{itemId}")
    public ResponseEntity<String>  itemStatus(@PathVariable Long itemId){
        try{
            return cartRestTemplate.itemsStatus(itemId);
        }
        catch(Exception e){
            System.out.println("VIEW ITEM STATUS:: "+e);
            if(e.getMessage().contains("INVALID ITEM ID"))
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("INVALID ITEM ID");
            if(e.getMessage().contains("ALREADY RETURNED"))
                return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
            else return ResponseEntity.status(HttpStatus.CONFLICT).body("UNKNOWN");
        }
    }
}
