package com.ijse.coursework.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.coursework.entity.Item;
import com.ijse.coursework.entity.Order;
import com.ijse.coursework.entity.OrderItem;
import com.ijse.coursework.repository.ItemRepository;
import com.ijse.coursework.repository.OrderItemRepository;
import com.ijse.coursework.repository.OrderRepository;
import com.ijse.coursework.repository.StockRepository;
import com.ijse.coursework.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private StockRepository stockRepository;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public Order creatOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order addItemToOrder(Long orderId, Long itemId, int qty){
        Order existOrder = orderRepository.findById(orderId).orElse(null);
        Item existItem = itemRepository.findById(itemId).orElse(null);
        if (existOrder==null||existItem==null) {
            return null;
        } 
        //  existOrder.getOrderedItems().add(existItem);

        OrderItem orderItem = new OrderItem();
        orderItem.setItem(existItem);
        orderItem.setOrder(existOrder);
        orderItem.setQty(qty);
        existOrder.setTotalPrice(existOrder.getTotalPrice()+(existItem.getPrice()*qty));
        orderItemRepository.save(orderItem); 
        return orderRepository.save(existOrder);     
    }

    @Override
    public Order removeItemFromOrder(Long orderId, Long itemId){
        Order existOrder = orderRepository.findById(orderId).orElse(null);
        Item existeItem = itemRepository.findById(itemId).orElse(null);
        if (existOrder==null||existeItem==null) {
            return null;
        }
        // existOrder.getOrderedItems().remove(existeItem);
        existOrder.setTotalPrice(existOrder.getTotalPrice()-existeItem.getPrice());
        return orderRepository.save(existOrder);
    }
    
    @Override
    public Order orderComplete(Long id){
        Order existOrder = orderRepository.findById(id).orElse(null);
        existOrder.setCompleted(true);
        return orderRepository.save(existOrder);
    }
    
}
