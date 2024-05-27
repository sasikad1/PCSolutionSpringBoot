package com.ijse.coursework.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {
     private Long id;
    private Double totalPrice;
    private LocalDateTime orderDate;
    private Boolean completed;
    // private List<Item> orderedItems;
}
