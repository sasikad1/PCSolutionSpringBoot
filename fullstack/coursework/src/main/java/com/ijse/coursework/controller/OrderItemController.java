package com.ijse.coursework.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.coursework.entity.OrderItem;
import com.ijse.coursework.service.OrderItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin(origins = "*")
public class OrderItemController {
    @Autowired
    private OrderItemService orderItemService;

    @GetMapping("/ordereditems")
    public List<OrderItem> getAllOrderItem(){
        return orderItemService.getOrderItems();
    }

    @GetMapping("/order/{id}/ordereditems")
    public List<OrderItem> getOrderItemById(@PathVariable Long id){
        return orderItemService.getOrderItemById(id);
    }
}
