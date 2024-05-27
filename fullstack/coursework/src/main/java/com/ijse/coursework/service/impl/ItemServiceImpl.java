package com.ijse.coursework.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.coursework.entity.Item;
import com.ijse.coursework.repository.ItemRepository;
import com.ijse.coursework.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService{
    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public Item getItemById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    @Override
    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public Item updaItem(Long id, Item item) {
        Item existItem = itemRepository.findById(id).orElse(null);
        if(existItem!=null){
            existItem.setName(item.getName());
            existItem.setPrice(item.getPrice());
            existItem.setDescription(item.getDescription());
            existItem.setItemCategory(item.getItemCategory());
            return itemRepository.save(existItem);
        }else{
            return null;
        }
    }

    @Override
    public void deleteItem(Long id) { 
        itemRepository.deleteById(id);
    }
}
