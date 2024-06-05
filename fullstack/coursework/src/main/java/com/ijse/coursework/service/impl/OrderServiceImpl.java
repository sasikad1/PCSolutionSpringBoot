package com.ijse.coursework.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.coursework.entity.Item;
import com.ijse.coursework.entity.Order;
import com.ijse.coursework.entity.OrderItem;
import com.ijse.coursework.entity.Stock;
import com.ijse.coursework.repository.ItemRepository;
import com.ijse.coursework.repository.OrderItemRepository;
import com.ijse.coursework.repository.OrderRepository;
import com.ijse.coursework.repository.StockRepository;
import com.ijse.coursework.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

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
    public Order addItemToOrder(Long orderId, Long itemId, int qty) {
        Order existOrder = orderRepository.findById(orderId).orElse(null);
        Item existItem = itemRepository.findById(itemId).orElse(null);
        Stock exiStock = stockRepository.findByItemId(itemId);

        if (existOrder == null || existItem == null || exiStock == null) {
            return null;
        }
        // existOrder.getOrderedItems().add(existItem);

        exiStock.setQty((exiStock.getQty()) - qty);
        stockRepository.save(exiStock);

        ///////////////////////////
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(existItem);
        orderItem.setOrder(existOrder);
        orderItem.setQty(qty);
        existOrder.setTotalPrice(existOrder.getTotalPrice() + (existItem.getPrice() * qty));
        orderItemRepository.save(orderItem);
        return orderRepository.save(existOrder);
    }

    @Override
    public Order removeItemFromOrder(Long orderId, Long itemId, Long orderItemId) {
        Order existOrder = orderRepository.findById(orderId).orElse(null);
        Item existeItem = itemRepository.findById(itemId).orElse(null);
        Stock existStock = stockRepository.findByItemId(itemId);
        if (existOrder == null || existeItem == null) {
            return null;
        }
        OrderItem orderItem = new OrderItem();
        int beforeQty = orderItem.getQty();

        existStock.setQty(existStock.getQty() + 1);
        orderItem.setQty((orderItem.getQty()) - 1);
        if (beforeQty > 1) {
            existOrder.setTotalPrice(existOrder.getTotalPrice() - existeItem.getPrice());
            orderItemRepository.save(orderItem);
            return orderRepository.save(existOrder);
        } else {
            orderItemRepository.deleteById(orderItemId);
            existOrder.setTotalPrice(existOrder.getTotalPrice() - existeItem.getPrice());
            orderRepository.save(existOrder);
            return existOrder;
        }
    }

    @Override
    public Order orderComplete(Long id) {
        Order existOrder = orderRepository.findById(id).orElse(null);
        existOrder.setCompleted(true);
        return orderRepository.save(existOrder);
    }

}
