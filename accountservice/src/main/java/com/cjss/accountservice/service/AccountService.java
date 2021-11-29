package com.cjss.accountservice.service;

import com.cjss.accountservice.entity.AddressEntity;
import com.cjss.accountservice.entity.CustomerEntity;
import com.cjss.accountservice.entity.OrderEntity;
import com.cjss.accountservice.model.AddressModel;
import com.cjss.accountservice.model.CustomerModel;
import com.cjss.accountservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class AccountService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    AuthenticationService authenticationService;



    //Customer registration
    public ResponseEntity customerRegistration(CustomerModel customerModel){
        CustomerEntity customerEntity ;
        if(customerRepository.findByEmail(customerModel.getEmail()) ==null){
          customerEntity = convertCustomerModelToEntity(customerModel);
          List<AddressEntity> addressEntityList = new ArrayList<>();
          List<OrderEntity> orderEntityList = new ArrayList<>();
/*
            if(customerModel.getAddressModelList()!=null)
            customerModel.getAddressModelList().forEach(x->{
                AddressEntity addressEntity;
                addressEntity = convertAddressModelToEntity(x);
                addressEntity.setCustomerEntity(customerEntity);
                addressEntityList.add(addressEntity);
            });
*/
          customerEntity.setAddressEntityList(addressEntityList);
          customerEntity.setOrderEntityList(orderEntityList);
          customerRepository.save(customerEntity);

          return ResponseEntity.status(HttpStatus.CREATED).body("REGISTRATION SUCCESSFUL\n");
      }
      else{
          return ResponseEntity.status(HttpStatus.CONFLICT).body("EMAIL ALREADY EXISTS\n");
      }
    }

    public ResponseEntity<String> login(String userEmail, String password){
        CustomerEntity customerEntity = customerRepository.findByEmail(userEmail);
        if(customerEntity!=null && new JasyptService().decryptPassword(customerEntity.getPassword()).equals(password)){
            return authenticationService.GenerateToken(userEmail);
        }
        else{
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("INVALID EMAIL OR PASSWORD");}
    }
    public String logout(String encryptedToken){
        if(authenticationService.validToken(encryptedToken) && !authenticationService.getTokenDetails(encryptedToken).isEmpty())

            // should develop invalidate token logic
            return "Logout Success";
        else{
            return "Not Logged In";
        }
    }


    // patching customer address
    public ResponseEntity<String> addAddress(AddressModel addressModel, String encryptedToken){
        if(encryptedToken.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("TOKEN EMPTY");
        }
        else if(authenticationService.validToken(encryptedToken)) {
                CustomerEntity customerEntity = customerRepository.findByEmail(authenticationService.getTokenDetails(encryptedToken));
                List<AddressEntity> addressEntityList;
                if (customerEntity.getAddressEntityList() == null) {
                    addressEntityList = new ArrayList<>();
                } else {
                    addressEntityList = customerEntity.getAddressEntityList();
                    AddressEntity addressEntity = convertAddressModelToEntity(addressModel);
                    addressEntity.setCustomerEntity(customerEntity);
                    addressEntityList.add(addressEntity);
                }
                customerRepository.save(customerEntity);
                return ResponseEntity.status(HttpStatus.CREATED).body("ADDRESS ADDED SUCCESSFULLY");
            }
        else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("INVALID TOKEN");
    }

    //getting customer details based on login(email)
    public ResponseEntity<String> getCustomerDetails(String encryptedToken){
        if(authenticationService.validToken(encryptedToken)){
            if(authenticationService.getTokenDetails(encryptedToken).isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Please Login\n");
            }else {
                CustomerEntity customerEntity = customerRepository.findByEmail(authenticationService.getTokenDetails(encryptedToken));
                return ResponseEntity.status(HttpStatus.OK).body(convertCustomerEntityToModel(customerEntity).toString());
            }
        }
        else {return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Token ...LOGIN AGAIN ");}
    }
    //Get all customers details
    public List<CustomerModel> getAllCustomers(){
        return customerRepository.findAll().stream().
                map(x-> convertCustomerEntityToModel(x)).collect(Collectors.toList());
    }

    //converting on CustomerModel  to CustomerEntity
    public CustomerEntity convertCustomerModelToEntity(CustomerModel customerModel){
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setFirstName(customerModel.getFirstName());
        customerEntity.setLastName(customerModel.getLastName());
        customerEntity.setEmail(customerModel.getEmail());

        customerEntity.setPassword(new JasyptService().encryptPassword(customerModel.getPassword()));
        customerEntity.setMobileNumber(customerModel.getMobileNumber());

        return customerEntity;
    }

    //converting AddressModel to AddressEntity
    public AddressEntity convertAddressModelToEntity(AddressModel addressModel){
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setLine1(addressModel.getLine1());
        addressEntity.setLine2(addressModel.getLine2());
        addressEntity.setCity(addressModel.getCity());
        addressEntity.setState(addressModel.getState());
        addressEntity.setPostalCode(addressModel.getPostalCode());
        addressEntity.setShippingAddress(addressModel.isShippingAddress());
        addressEntity.setBillingAddress(addressModel.isBillingAddress());
        return addressEntity;
    }

    //converting AddressEntity to AddressModel
    public AddressModel convertAddressEntityToModel(AddressEntity addressEntity){
        AddressModel addressModel = new AddressModel();
        addressModel.setAddressId(addressEntity.getAddressId());
        addressModel.setLine1(addressEntity.getLine1());
        addressModel.setLine2(addressEntity.getLine2());
        addressModel.setCity(addressEntity.getCity());
        addressModel.setState(addressEntity.getState());
        addressModel.setPostalCode(addressEntity.getPostalCode());
        addressModel.setBillingAddress(addressEntity.isBillingAddress());
        addressModel.setShippingAddress(addressEntity.isShippingAddress());
        return addressModel;
    }

    //converting CustomerEntity to CustomerModel
    public CustomerModel convertCustomerEntityToModel(CustomerEntity customerEntity){
        CustomerModel customerModel = new CustomerModel();
        customerModel.setFirstName(customerEntity.getFirstName());
        customerModel.setLastName(customerEntity.getLastName());
        customerModel.setEmail(customerEntity.getEmail());
        customerModel.setPassword(customerEntity.getEmail());
        customerModel.setPassword(null);
        customerModel.setMobileNumber(customerEntity.getMobileNumber());

        List<AddressModel> addressModelList = new ArrayList<>();
        customerEntity.getAddressEntityList().forEach(x->{
            addressModelList.add(convertAddressEntityToModel(x));
        });

        customerModel.setAddressModelList(addressModelList);
        return customerModel;
    }



}
