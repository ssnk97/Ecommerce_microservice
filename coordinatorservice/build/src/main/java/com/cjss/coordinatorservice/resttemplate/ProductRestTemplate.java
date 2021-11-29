package com.cjss.coordinatorservice.resttemplate;

import com.cjss.coordinatorservice.model.CustomerModel;
import com.cjss.coordinatorservice.model.ProductModel;
import com.cjss.coordinatorservice.model.ProductPriceModel;
import com.cjss.coordinatorservice.model.ProductSizeModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

@Service
public class ProductRestTemplate {

    public ResponseEntity<String> addProduct( ProductModel productModel) throws Exception{
        try{
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8082/cjss-customer/add-product";
        HttpEntity<ProductModel> request = new HttpEntity<>(productModel);
        ResponseEntity<String> response = restTemplate
                .exchange(fooResourceUrl, HttpMethod.POST, request, String.class);
        return response;
        }
        catch(Exception e){
            throw  e;
        }
    }

    public ResponseEntity<String> addSkusSize(ProductSizeModel productSizeModel) {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8082/cjss-customer/add-sku";
        HttpEntity<ProductSizeModel> request = new HttpEntity<>(productSizeModel);
        ResponseEntity<String> response = restTemplate
                .exchange(fooResourceUrl, HttpMethod.POST, request, String.class);
        return response;
    }

    public ResponseEntity<String> addPrice(@Valid @RequestBody ProductPriceModel productPriceModel){
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8082/cjss-customer/add-price";
        HttpEntity<ProductPriceModel> request = new HttpEntity<>(productPriceModel);
        ResponseEntity<String> response = restTemplate
                .exchange(fooResourceUrl, HttpMethod.POST, request, String.class);
        return response;
    }

}
