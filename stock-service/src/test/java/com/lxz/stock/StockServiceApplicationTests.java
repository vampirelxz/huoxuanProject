package com.lxz.stock;

import com.lxz.stock.controller.StockInfoController;
import com.lxz.stock.dao.StockListMapper;
import com.lxz.stock.entity.PersonalStock;
import com.lxz.stock.entity.StockBO;
import com.lxz.stock.service.StockInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
class StockServiceApplicationTests {
    @Autowired
    StockInfoController stockInfoController;
    @Autowired
    StockInfoService stockInfoService;
    @Autowired
    StockListMapper stockListMapper;

    @Test
    void contextLoads() {
        stockInfoController.listRealTimeStock("300378");
    }

    @Test
    void contextLoads2() {
        stockInfoController.listTodayStock("300378");
    }

    @Test
    void personalStock() throws IOException {
        List<PersonalStock> stocks = stockInfoService.selfStock(10001);
        System.out.println(stocks);
    }

    @Test
    void personalDelete(){
        stockInfoService.deleteSelfStock(10001,"300377");
    }

    @Test
    void personalInsert(){
        stockInfoService.insertSelfStock(10001,"300377");
    }

    @Test
    void checkPersonStock(){
        List<StockBO> stockBOS = stockListMapper.checkPersonalStock(10002, "300377");
        System.out.println(stockBOS);
    }
}
