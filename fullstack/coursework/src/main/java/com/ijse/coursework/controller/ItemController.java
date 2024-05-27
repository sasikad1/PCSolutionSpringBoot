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

import com.ijse.coursework.dto.ItemDto;
import com.ijse.coursework.entity.Item;
import com.ijse.coursework.entity.ItemCategory;
import com.ijse.coursework.service.ItemCategoryService;
import com.ijse.coursework.service.ItemService;


@RestController
@CrossOrigin(origins = "*")
public class ItemController {
    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemCategoryService itemCategoryService;

    @GetMapping("/items")
    public List<Item> getAllItems(){
        return itemService.getAllItems();
    }

    @GetMapping("/item/{id}")
    public Item getByItem(@PathVariable Long id){
        return itemService.getItemById(id);
    }

    @PostMapping("/item")
    public Item creaItem (@RequestBody ItemDto itemDto){
        Item item1 = new Item();
        item1.setName(itemDto.getName());
        item1.setPrice(itemDto.getPrice());
        item1.setDescription(itemDto.getDescription());

        ItemCategory category = itemCategoryService.getItemCategoryById(itemDto.getItemcategoryId());
        item1.setItemCategory(category);
        return itemService.createItem(item1);
    }

    @PutMapping("/item/{id}")
    public Item updaItem(@PathVariable Long id, @RequestBody ItemDto itemDto){
        Item item2 = new Item();
        item2.setName(itemDto.getName());
        item2.setPrice(itemDto.getPrice());
        item2.setDescription(itemDto.getDescription());
// Git
        ItemCategory category = itemCategoryService.getItemCategoryById(itemDto.getItemcategoryId());
        item2.setItemCategory(category);
        return itemService.updaItem(id, item2);
    }

    @DeleteMapping("/item/{id}")
    public void deleteItem(@PathVariable Long id){
        itemService.deleteItem(id);
    }
}
