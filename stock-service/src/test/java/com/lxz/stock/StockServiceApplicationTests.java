package com.lxz.stock;

import com.lxz.stock.controller.StockInfoController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StockServiceApplicationTests {
    @Autowired
    StockInfoController stockInfoController;
    @Test
    void contextLoads() {
        stockInfoController.listRealTimeStock(300378);
    }

    @Test
    void contextLoads2() {
        stockInfoController.listTodayStock(300378);
    }
}
