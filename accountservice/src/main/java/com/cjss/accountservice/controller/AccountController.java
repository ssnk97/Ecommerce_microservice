package com.cjss.accountservice.controller;


import com.cjss.accountservice.model.AddressModel;
import com.cjss.accountservice.model.CustomerModel;
import com.cjss.accountservice.model.LoginModel;
import com.cjss.accountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AccountController {
    @Autowired
    AccountService accountService;

    @RequestMapping(value ="cjss-customer/register", method = RequestMethod.POST)
    public ResponseEntity customerRegistration(@Valid  @RequestBody CustomerModel customerModel){
        if(customerModel == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("REQUEST BODY CANNOT BE EMPTY");
        }
        return accountService.customerRegistration(customerModel);
    }

    @RequestMapping(value ="/cjss-customer/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(@Valid @RequestBody LoginModel loginModel)  {
        String userEmail = loginModel.getUserEmail();
        String password = loginModel.getPassword();

                ResponseEntity<String> e =accountService.login(userEmail, password);
                //System.out.println(e.getStatusCode() + " "+ e.getBody());
                return e;
    }

    @RequestMapping(value="/cjss-customer/logout", method = RequestMethod.GET)
    public String logout(@RequestHeader String encryptedToken){
        return accountService.logout(encryptedToken);
    }



    @RequestMapping(value="/cjss-customer/add-address", method = RequestMethod.POST)
    public ResponseEntity<String> addAddress(@Valid @RequestBody AddressModel addressModel, @RequestHeader String encryptedToken){
        return accountService.addAddress(addressModel, encryptedToken);
    }

    @GetMapping("/cjss-customer/get-customer-details")
    public ResponseEntity<String> getCustomerDetails(@RequestHeader String encryptedToken){
        return accountService.getCustomerDetails(encryptedToken);

    }

//    @RequestMapping(value ="/cjss-admin/get-all-customers", method = RequestMethod.GET)
//    public List<CustomerModel> getAllCustomers(){
//        return accountService.getAllCustomers();
//    }
}
