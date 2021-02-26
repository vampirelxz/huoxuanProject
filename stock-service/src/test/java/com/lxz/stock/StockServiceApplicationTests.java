package com.lxz.stock;

import com.lxz.stock.controller.StockInfoController;
import com.lxz.stock.service.StockInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class StockServiceApplicationTests {
    @Autowired
    StockInfoController stockInfoController;
    @Autowired
    StockInfoService stockInfoService;

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
        String stocks = stockInfoService.selfStock(10001);
        System.out.println(stocks);
    }
}
