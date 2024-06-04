package com.ijse.coursework.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.coursework.entity.OrderItem;
import com.ijse.coursework.repository.OrderItemRepository;
import com.ijse.coursework.service.OrderItemService;
import com.ijse.coursework.service.OrderService;

@Service
public class OrderItemServiceImpls implements OrderItemService{
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public List<OrderItem> getOrderItems() {
       return orderItemRepository.findAll();
    }

    @Override
    public List<OrderItem> getOrderItemById(Long id){
        return orderItemRepository.findAllByOrderId(id);
    }
}
