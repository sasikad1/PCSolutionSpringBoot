package com.ijse.coursework.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.coursework.entity.Item;
import com.ijse.coursework.entity.Stock;
import com.ijse.coursework.repository.ItemRepository;
import com.ijse.coursework.repository.StockRepository;
import com.ijse.coursework.service.ItemService;
import com.ijse.coursework.service.StockService;

@Service
public class StockServiceImpl implements StockService{

    @Autowired
    private StockRepository stockRepository;

    @Override
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    @Override
    public Stock getStockById(Long id) {
       return stockRepository.findById(id).orElse(null);
    }

    @Override
    public Stock createStock(Stock stock) {
        Stock stock1 = stockRepository.findByItemId(stock.getItem().getId());
        if(stock1==null){
            return stockRepository.save(stock);
        }
       return null;
    }

    @Override
    public Stock updateStock(Long id, Stock stock) {
        Stock exsiStock = stockRepository.findById(id).orElse(null);
        if (exsiStock!=null) {
            exsiStock.setQty(stock.getQty());
            exsiStock.setLocation(stock.getLocation());
            exsiStock.setItem(stock.getItem());
            return stockRepository.save(exsiStock);
        }else{
            return null;
        }
    }

    @Override
    public void deleteStock(Long id) {
        stockRepository.deleteById(id);
    }
}
