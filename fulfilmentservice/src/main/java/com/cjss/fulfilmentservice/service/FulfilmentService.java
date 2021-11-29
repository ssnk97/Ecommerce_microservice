package com.cjss.fulfilmentservice.service;

import com.cjss.fulfilmentservice.entity.ItemsOrderedEntity;
import com.cjss.fulfilmentservice.repository.ItemsOrderedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FulfilmentService {
    @Autowired
    ItemsOrderedRepository itemsOrderedRepository;
    public String updateItemStatus(Long itemId, String status){
        Optional<ItemsOrderedEntity> item = itemsOrderedRepository.findById(itemId);
        if(item.isPresent()) {
            item.get().setItemStatus(status);
            itemsOrderedRepository.save(item.get());
            return "STATUS UPDATED : "+item.get().getItemStatus();
        }
        else return "INVALID ID";
    }
}
