package com.ijse.coursework.entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Entity
@Getter
@Component
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private int qty;

    // public OrderItem(String item, int qty, int price) {
    //     this.item=item;

        
    // }

    // public void OrderItem(Long id, OrderItem orderItem, Item item, int qty) {
    //     this.id = id;
    //     this.order = order;
    //     this.item = item;
    //     this.qty = qty;
    // }

}
