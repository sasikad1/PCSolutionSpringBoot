package com.ijse.coursework.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double totalPrice;

    @Column(nullable = false)
    private LocalDateTime orderDate;

    @Column(columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean completed;

    // @ManyToMany
    // @JoinTable(name="order_item",
    //     joinColumns = @JoinColumn(name="order_id"),
    //     inverseJoinColumns = @JoinColumn(name = "item_id")
    // )
    // private List<Item> orderedItems;
////////////////////////////////////////////////////

    @JsonIgnore
    @OneToMany(mappedBy = "order")
    private Set<OrderItem> orderedItems;


    public void addOrderItem(OrderItem orderItem) {
        orderedItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void removeOrderItem(OrderItem orderItem) {
        orderedItems.remove(orderItem);
        orderItem.setOrder(null);
    }
}
