package com.cjss.coordinatorservice.controller;

import com.cjss.coordinatorservice.model.AddressModel;
import com.cjss.coordinatorservice.model.CustomerModel;
import com.cjss.coordinatorservice.model.LoginModel;
import com.cjss.coordinatorservice.resttemplate.AccountRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AccountRestController {
    @Autowired
    AccountRestTemplate accountRestTemplate;

    @RequestMapping(value ="coordinator/customer/register", method = RequestMethod.POST)
    public ResponseEntity<String> customerRegistration(@Valid @RequestBody CustomerModel customerModel){
       try {
           return accountRestTemplate.customerRegistration(customerModel);
       }
       catch(Exception e){
           System.out.println("REGISTRATION CATCH :"+e);
           if(e.getMessage().contains("EMAIL ALREADY EXISTS"))
               return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
           else return ResponseEntity.status(HttpStatus.CONFLICT).body("UNKNOWN");
       }
    }

    @RequestMapping(value ="coordinator/customer/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(@Valid @RequestBody LoginModel loginModel) {
        try{
            return accountRestTemplate.login(loginModel);
        }catch (Exception e){
            System.out.println("LOGIN Catch:"+e);
            if(e.getMessage().contains("401"))
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("INVALID USERNAME OR PASSWORD");
            else
                return ResponseEntity.status(HttpStatus.CONFLICT).body("UNKNOWN");
        }
    }


    @RequestMapping(value="coordinator/customer/add-address", method = RequestMethod.POST)
    public ResponseEntity<String> addAddress(@Valid @RequestBody AddressModel addressModel, @RequestHeader String encryptedToken){
        try{
            return accountRestTemplate.addAddress(addressModel, encryptedToken);
        }
        catch (Exception e){
            System.out.println("COORD:: address:" + e);
            if(e.getMessage().contains("TOKEN EMPTY"))
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
            if(e.getMessage().contains("401"))
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED! PLEASE LOGIN");
            else
                return ResponseEntity.status(HttpStatus.CONFLICT).body("UNKNOWN");
        }
    }

}
