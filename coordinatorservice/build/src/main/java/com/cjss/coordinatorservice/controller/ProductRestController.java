package com.cjss.coordinatorservice.controller;

import com.cjss.coordinatorservice.model.ProductModel;
import com.cjss.coordinatorservice.model.ProductPriceModel;
import com.cjss.coordinatorservice.model.ProductSizeModel;
import com.cjss.coordinatorservice.resttemplate.ProductRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ProductRestController {
    @Autowired
    ProductRestTemplate productRestTemplate;

    @PostMapping("/coordinator/customer/add-product")
    public ResponseEntity<String> addProduct(@Valid @RequestBody ProductModel productModel){
        try{
            return productRestTemplate.addProduct(productModel);
        }
        catch(Exception e){
            System.out.println("ADD PRODUCT Catch:"+e);

            if(e.getMessage().contains("REQUEST BODY EMPTY"))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            if(e.getMessage().contains("PRODUCT CODE ALREADY EXISTS")) {

                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            }
            else return  ResponseEntity.status(HttpStatus.CONFLICT).body("UNKNOWN");
        }
    }

    @PostMapping("/coordinator/customer/add-sku")
    public ResponseEntity<String> addSkusSize(@Valid @RequestBody ProductSizeModel productSizeModel){
        try {
            return productRestTemplate.addSkusSize(productSizeModel);
        }catch (Exception e){
            System.out.println("ADD SKU  CATCH "+e);
//            if(e.getMessage().contains("SKU ADDED"))
//                return ResponseEntity.status(HttpStatus.CREATED).body(e.getMessage());
            if(e.getMessage().contains("PRODUCT CODE NOT FOUND OR SKU ALREADY AVAILABLE"))
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            else return  ResponseEntity.status(HttpStatus.CONFLICT).body("UNKNOWN");
        }
    }

    @PostMapping("coordinator/customer/add-price")
    public ResponseEntity<String> addPrice(@Valid @RequestBody ProductPriceModel productPriceModel){
        try {
            return productRestTemplate.addPrice(productPriceModel);
        }catch(Exception e){
            System.out.println("CATCH addPrice: "+e);
            if(e.getMessage().contains("PRICE ADDED"))
                return ResponseEntity.status(HttpStatus.CREATED).body(e.getMessage());
            if(e.getMessage().contains("SKU CODE NOT FOUND OR AVAILABLE IN PRICE REPO"))
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            else return  ResponseEntity.status(HttpStatus.CONFLICT).body("UNKNOWN");
        }
    }
}
