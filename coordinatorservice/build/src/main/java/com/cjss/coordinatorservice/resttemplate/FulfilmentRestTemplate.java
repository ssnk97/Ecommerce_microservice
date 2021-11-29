package com.cjss.coordinatorservice.resttemplate;


import com.cjss.coordinatorservice.model.CartModel;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FulfilmentRestTemplate {
    public ResponseEntity updateItemStatus(Long itemId, String status){
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8085/cjss-customer/update-item-status?itemId="+itemId+"&status="+status;
        HttpHeaders header = new HttpHeaders();
        HttpEntity<CartModel> request = new HttpEntity<>(header);
        ResponseEntity<String> response = restTemplate
                .exchange(fooResourceUrl, HttpMethod.POST, request, String.class);
        return response;
    }
}
