package com.ijse.coursework.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.coursework.dto.StockDto;
import com.ijse.coursework.entity.Item;
import com.ijse.coursework.entity.Stock;
import com.ijse.coursework.service.ItemService;
import com.ijse.coursework.service.StockService;

/**
 * StockController
 */
@RestController
@CrossOrigin(origins = "*")
public class StockController {

    @Autowired
    private StockService stockService;

    @Autowired
    private ItemService itemService;

    @GetMapping("/stocks")
    public List<Stock> getAllStocks(){
        return stockService.getAllStocks();
    }
    
    @GetMapping("/stock/{id}")
    public Stock getStockById(@PathVariable Long id){
        return stockService.getStockById(id);
    }

    @PostMapping("/stock")
    public Stock createStock(@RequestBody StockDto stockDto){
        Stock stock = new Stock();
        stock.setQty(stockDto.getQty());
        stock.setLocation(stockDto.getLocation());
        // stock.setItem(stockDto.getItemId());
        Item item = itemService.getItemById(stockDto.getItemId());
        stock.setItem(item);

        return stockService.createStock(stock);
    }

    @PutMapping("/stock/{id}")
    public Stock updateStock(@PathVariable Long id, @RequestBody StockDto stockDto){
        Stock stock  = new Stock();
        stock.setQty(stockDto.getQty());
        stock.setLocation(stockDto.getLocation());

        Item item = itemService.getItemById(stockDto.getItemId());
        stock.setItem(item);

        return stockService.updateStock(id, stock);
    }

    @DeleteMapping("/stock/{id}")
    public void deleteStock(@PathVariable Long id){
        stockService.deleteStock(id);
    }
}