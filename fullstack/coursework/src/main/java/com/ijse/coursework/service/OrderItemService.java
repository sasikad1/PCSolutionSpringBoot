package com.ijse.coursework.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.coursework.entity.OrderItem;

@Service
public interface OrderItemService {
    List<OrderItem> getOrderItems();

    List<OrderItem> getOrderItemById(Long id);
}
