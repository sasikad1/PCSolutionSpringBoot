package com.ijse.coursework.entity;


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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Entity
@Getter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String name;

    @Column(nullable = true)
    private Double price;

    private String description;
    
    // @ManyToOne
    // @JoinColumn(name="item_category_id")
    // private ItemCategory itemCategory;

    @ManyToOne
    @JoinColumn(name="category_id")
    private ItemCategory itemCategory;

    @JsonIgnore
    @OneToOne(mappedBy = "item")
    private Stock stock;

    // @JsonIgnore
    // @ManyToMany(mappedBy = "orderedItems")
    // private List<Order> orders;
//////////////////////////////////////////////////
    @JsonIgnore
    @OneToMany(mappedBy = "item")
    private Set<OrderItem> orderedItems;
}
