package com.cjss.productservice.controller;


import com.cjss.productservice.entity.*;
import com.cjss.productservice.model.*;
import com.cjss.productservice.repository.*;

import com.cjss.productservice.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ProductsController {
    @Autowired
    ProductsService productsService;

    @PostMapping("/cjss-customer/add-product")
    public ResponseEntity<String> addProduct(@Valid @RequestBody ProductModel productModel){
        if(productModel == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("REQUEST BODY EMPTY");

        String message = productsService.addProduct(productModel);

        if(message.contains("PRODUCT ADDED"))
            return ResponseEntity.status(HttpStatus.CREATED).body(message);
        if(message.equals("PRODUCT CODE ALREADY EXISTS")) {
            System.out.println("PRODUCT EXISTS");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
        }
        else return  ResponseEntity.status(HttpStatus.CONFLICT).body("UNKNOWN");

    }

    @PostMapping("/cjss-customer/add-sku")
    public ResponseEntity<String> addSkusSize(@Valid @RequestBody ProductSizeModel productSizeModel){
        if(productSizeModel == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("REQUEST BODY EMPTY");
        String message = productsService.addSkusSize(productSizeModel);
        if(message.contains("SKU ADDED"))
            return ResponseEntity.status(HttpStatus.CREATED).body(message);
        if(message.equals("PRODUCT CODE NOT FOUND OR SKU ALREADY AVAILABLE"))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        else return  ResponseEntity.status(HttpStatus.CONFLICT).body("UNKNOWN");

    }

    @PostMapping("/cjss-customer/add-price")
    public ResponseEntity <String>addPrice(@Valid @RequestBody ProductPriceModel productPriceModel){

        String message = productsService.addPrice(productPriceModel);
        if(message.contains("PRICE ADDED"))
            return ResponseEntity.status(HttpStatus.CREATED).body(message);
        if(message.contains("SKU CODE NOT FOUND OR AVAILABLE IN PRICE REPO"))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        else return  ResponseEntity.status(HttpStatus.CONFLICT).body("UNKNOWN");
    }
}
