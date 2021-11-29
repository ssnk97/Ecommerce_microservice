package com.cjss.coordinatorservice.resttemplate;

import com.cjss.coordinatorservice.model.CartModel;
import com.cjss.coordinatorservice.model.InventoryModel;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class CartRestTemplate {

    public ResponseEntity<String>  addToCart(CartModel cartModel, String encryptedToken){
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8084/cjss-customer/add-cart";
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        header.add("encryptedToken", encryptedToken);
        HttpEntity<CartModel> request = new HttpEntity<>(cartModel, header);
        ResponseEntity<String> response = restTemplate
                .exchange(fooResourceUrl, HttpMethod.POST, request, String.class);
        return response;
    }

    public ResponseEntity<String> viewCart(String encryptedToken){
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8084/cjss-customer/view-cart";
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        header.add("encryptedToken", encryptedToken);

        HttpEntity<?> request = new HttpEntity<>(header);
        ResponseEntity<String> response = restTemplate
                .exchange(fooResourceUrl, HttpMethod.GET, request, String.class);
        return response;
    }

    public ResponseEntity<String> placeOrder(Long addressId, String encryptedToken) {
        RestTemplate restTemplate = new RestTemplate();
       // String address = String.valueOf(addressId);
        String fooResourceUrl = "http://localhost:8084/cjss-customer/place-order?addressId="+addressId;
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        header.add("encryptedToken", encryptedToken);
        HttpEntity<CartModel> request = new HttpEntity<>(header);
        ResponseEntity<String> response = restTemplate
                .exchange(fooResourceUrl, HttpMethod.POST, request, String.class);
        return response;
    }


    public ResponseEntity<String> itemsStatus(Long itemId){
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8084/cjss-customer/get-item-status/"+itemId;

        ResponseEntity<String> response = restTemplate
                .getForEntity(fooResourceUrl, String.class);
        return response;
    }
}
