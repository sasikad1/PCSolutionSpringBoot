package com.ijse.coursework.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.ijse.coursework.entity.Item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {
     private Long id;
    private Double totalPrice;
    private LocalDateTime orderDate;
    private Boolean completed;
    private List<Item> orderedItems;
}
