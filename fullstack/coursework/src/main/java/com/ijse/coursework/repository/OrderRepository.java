package com.ijse.coursework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ijse.coursework.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
    
}
