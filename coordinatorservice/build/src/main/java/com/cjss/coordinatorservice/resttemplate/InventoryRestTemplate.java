package com.cjss.coordinatorservice.resttemplate;

import com.cjss.coordinatorservice.model.InventoryModel;
import com.cjss.coordinatorservice.model.ProductSizeModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class InventoryRestTemplate {

    public ResponseEntity<String> addInventory(InventoryModel inventoryModel, String skuCode){
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8083/cjss-customer/add-inventory?skuCode="+skuCode;
        HttpEntity<InventoryModel> request = new HttpEntity<>(inventoryModel);
        ResponseEntity<String> response = restTemplate
                .exchange(fooResourceUrl, HttpMethod.POST, request, String.class);
        return response;
    }
}
