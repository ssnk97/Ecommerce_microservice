package com.cjss.coordinatorservice.resttemplate;

import com.cjss.coordinatorservice.model.CartModel;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ReturnRestTemplate {

    public ResponseEntity<String> returnItem(Long itemId, String encryptedToken){
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8086/cjss-customer/return-item/{itemId}";
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        header.add("encryptedToken", encryptedToken);
        HttpEntity<CartModel> request = new HttpEntity<>(header);
        ResponseEntity<String> response = restTemplate
                .exchange(fooResourceUrl, HttpMethod.POST, request, String.class, itemId);
        return response;
    }
}
