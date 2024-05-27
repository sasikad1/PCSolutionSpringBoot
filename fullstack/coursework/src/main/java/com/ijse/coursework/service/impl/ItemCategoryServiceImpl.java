package com.ijse.coursework.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.coursework.entity.ItemCategory;
import com.ijse.coursework.repository.ItemCategoryRepository;
import com.ijse.coursework.service.ItemCategoryService;

@Service
public class ItemCategoryServiceImpl implements ItemCategoryService{

    @Autowired
    private ItemCategoryRepository itemCategoryRepository;

    @Override
    public List<ItemCategory> getAllItemCategory() {
       return itemCategoryRepository.findAll();
    }

    @Override
    public ItemCategory getItemCategoryById(Long id) {
        return itemCategoryRepository.findById(id).orElse(null);
    }

    @Override
    public ItemCategory createItemCategory(ItemCategory itemCategory) {
       return itemCategoryRepository.save(itemCategory);
    }

    @Override
    public ItemCategory updateItemCategory(Long id, ItemCategory itemCategory) {
       ItemCategory existItemCategory =  itemCategoryRepository.findById(id).orElse(null);
        if (existItemCategory!=null) {
            existItemCategory.setName(itemCategory.getName());
            return itemCategoryRepository.save(existItemCategory);
        }else{
            return null;
        }

    }

    @Override
    public void deleteItemCategory(Long id){
         itemCategoryRepository.deleteById(id);
    }
    
}

