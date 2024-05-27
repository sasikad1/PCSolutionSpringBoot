package com.ijse.coursework.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.coursework.entity.ItemCategory;

@Service
public interface ItemCategoryService{
    List<ItemCategory> getAllItemCategory();

    ItemCategory getItemCategoryById(Long id);

    ItemCategory createItemCategory(ItemCategory itemCategory);

    ItemCategory updateItemCategory(Long id, ItemCategory itemCategory);

    void deleteItemCategory(Long id);
}
