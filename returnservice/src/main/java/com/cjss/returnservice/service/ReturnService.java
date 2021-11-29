package com.cjss.returnservice.service;

import com.cjss.returnservice.entity.CustomerEntity;
import com.cjss.returnservice.entity.InventoryEntity;
import com.cjss.returnservice.entity.ItemsOrderedEntity;
import com.cjss.returnservice.repository.CustomerRepository;
import com.cjss.returnservice.repository.InventoryRepository;
import com.cjss.returnservice.repository.ItemsOrderedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReturnService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ItemsOrderedRepository itemsOrderedRepository;
    @Autowired
    InventoryRepository inventoryRepository;
    public String returnItem(Long itemId, String email){
        Optional<CustomerEntity> customerEntity = Optional.ofNullable(customerRepository.findByEmail(email));
        ItemsOrderedEntity itemsOrderedEntity= itemsOrderedRepository.findById(itemId).get();

        if(customerEntity.isPresent() && !itemsOrderedEntity.getItemStatus().equals("RETURN")){
          Optional<ItemsOrderedEntity> item =itemsOrderedRepository.findById(itemId);
          if(item.isPresent()){
                InventoryEntity inventoryEntity = inventoryRepository.findBySkuCode(item.get().getSkuCode());
                inventoryEntity.setQuantityAvailable(inventoryEntity.getQuantityAvailable()+item.get().getQuantity());
                item.get().setItemStatus("RETURN");
                inventoryRepository.save(inventoryEntity);
            return "RETURN ACCEPTED\n ITEM ID: "+ item.get().getItemId() + "\tREFUND AMOUNT: "+ (Double.parseDouble(item.get().getPrice()) * Integer.parseInt(item.get().getQuantity()));
          }else return "INVALID ITEM ID";
        }
        else return "ALREADY RETURNED";
    }
}
