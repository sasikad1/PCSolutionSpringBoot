package com.ijse.coursework.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.coursework.dto.ItemCategoryDto;
import com.ijse.coursework.entity.ItemCategory;
import com.ijse.coursework.service.ItemCategoryService;

@RestController
@CrossOrigin(origins = "*")
public class ItemCategoryController {
    @Autowired
    private ItemCategoryService itemCategoryService;

    @GetMapping("/itemcategorys")
    public List<ItemCategory> getAllItemCategory(){
        return itemCategoryService.getAllItemCategory();
    }

    @GetMapping("/itemcategory/{id}")
    public ItemCategory getItemCategoryById(Long id){
        return itemCategoryService.getItemCategoryById(id);
    }

    @PostMapping("/itemcategory")
    public ItemCategory createItemCategory(@RequestBody ItemCategoryDto itemCategoryDto){
        ItemCategory itemCategory  = new ItemCategory();
        itemCategory.setName(itemCategoryDto.getName());
        return itemCategoryService.createItemCategory(itemCategory);
    }

    @PutMapping("/itemcategory/{id}")
    public ItemCategory updateItemCategory(@PathVariable Long id, @RequestBody ItemCategoryDto itemCategoryDto){
        ItemCategory itemCategory = new ItemCategory();
        itemCategory.setName(itemCategoryDto.getName());
        return itemCategoryService.updateItemCategory(id, itemCategory);
    }

    @DeleteMapping("/itemcategory/{id}")
    public void deleteItemCategoryById(@PathVariable Long id){
        itemCategoryService.deleteItemCategory(id);
    }
}
