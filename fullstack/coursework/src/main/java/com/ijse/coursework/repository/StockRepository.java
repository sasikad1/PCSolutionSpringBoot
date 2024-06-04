package com.ijse.coursework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ijse.coursework.entity.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long>{
    Stock findByItemId(Long id);
}