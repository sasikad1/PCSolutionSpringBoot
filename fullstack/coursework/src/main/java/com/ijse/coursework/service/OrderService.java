package com.ijse.coursework.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.coursework.entity.Order;

@Service
public interface OrderService {
    List<Order> getAllOrders();

    Order getOrderById(Long id);

    Order creatOrder(Order order);

    Order orderComplete(Long id);

    Order addItemToOrder(Long orderId, Long itemId, int qty);

    Order removeItemFromOrder(Long orderId, Long itemId, Long orderItemId);
}
