package com.cjss.coordinatorservice.resttemplate;
import com.cjss.coordinatorservice.model.AddressModel;
import com.cjss.coordinatorservice.model.CustomerModel;
import com.cjss.coordinatorservice.model.LoginModel;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.net.URI;

@Service
public class AccountRestTemplate {
    public ResponseEntity<String> customerRegistration(CustomerModel customerModel) throws Exception
    {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String fooResourceUrl = "http://localhost:8081/cjss-customer/register";
            HttpEntity<CustomerModel> request = new HttpEntity<>(customerModel);
            ResponseEntity<String> response = restTemplate
                    .exchange(fooResourceUrl, HttpMethod.POST, request, String.class);
            return response;
        }
        catch(Exception e ){
            throw e;
        }
    }


    public ResponseEntity<String> login(LoginModel loginModel) throws Exception{
       try {
           RestTemplate restTemplate = new RestTemplate();
           String fooResourceUrl = "http://localhost:8081/cjss-customer/login";
           HttpEntity<LoginModel> request = new HttpEntity<>(loginModel);
           ResponseEntity<String> response = restTemplate
                   .exchange(fooResourceUrl, HttpMethod.POST, request, String.class);

           return response;
       }catch(Exception e){
           throw e;
       }
    }

    public ResponseEntity<String> addAddress(AddressModel addressModel, String encryptedToken) throws Exception{
       try{ RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8081/cjss-customer/add-address";
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        header.add("encryptedToken", encryptedToken);

        HttpEntity<AddressModel> entity= new HttpEntity<>(addressModel, header);
        ResponseEntity<String> response = restTemplate
                .exchange(fooResourceUrl , HttpMethod.POST, entity, String.class);
        return response;
       }
       catch(Exception e){
           throw e;
       }
    }



}
