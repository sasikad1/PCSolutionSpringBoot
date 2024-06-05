package com.ijse.coursework.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.coursework.dto.OrderItemDto;
import com.ijse.coursework.entity.Order;
import com.ijse.coursework.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin(origins = "*")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public List<Order> getOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/order/{id}")
    public Order getByOrder(@PathVariable Long id){
        return orderService.getOrderById(id);
    }

    // create order without items
    @PostMapping("/auth/order")
    public Order createOrder() {
       Order order = new Order();
       order.setTotalPrice(0.0);
       order.setOrderDate(LocalDateTime.now());
       order.setCompleted(false);
    //    order.setOrderedItems(null);
       return orderService.creatOrder(order);
    }

    //item add to order
    @PostMapping("/order/{orderId}/addItem")
    public Order addItemToOrder(@PathVariable Long orderId, @RequestBody OrderItemDto orderItemDto){
        return orderService.addItemToOrder(orderId, orderItemDto.getItemId(), orderItemDto.getQty());
    }

    @DeleteMapping("/order/{orderId}/item/{itemId}/orderItem")
    public Order removeItemFromOrder(@PathVariable Long orderId, @PathVariable Long itemId, @RequestBody OrderItemDto orderItemDto){
        return orderService.removeItemFromOrder(orderId, itemId, orderItemDto.getId());
    }

    @PostMapping("/order/completed/{id}")
    public Order orderCompleted(@PathVariable Long id){
        return orderService.orderComplete(id);
    }
}