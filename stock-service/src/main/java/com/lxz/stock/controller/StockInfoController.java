package com.lxz.stock.controller;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/1/14/11:35
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.stock.controller
 * 文件描述: @Description: 股票信息控制层
 *
 * All rights Reserved, Designed By 财富管理部
 * @Copyright:2016-2021
 *
 ********************************************************/

import com.lxz.stock.entity.BaseStock;
import com.lxz.stock.entity.BuyStock;
import com.lxz.stock.entity.PersonalStock;
import com.lxz.stock.entity.Stock;
import com.lxz.stock.service.StockInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * 包名称： com.lxz.stock.controller
 * 类名称：StockInfoController
 * 类描述：股票信息控制层
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/1/14/11:35
 */

@RestController
public class StockInfoController {
    @Autowired
    StockInfoService stockInfoService;

    @GetMapping("/realtimeStock")
    public BuyStock listRealTimeStock(String code){
        if (code == null){
            code ="300377";
        }
        BuyStock buyStock = stockInfoService.infoRealTime(code);
        System.out.println(buyStock.getCode());
        return buyStock;
    }

    @GetMapping("/todayStock")
    public  Stock listTodayStock(String code){
        if(code == null){
            code ="300377";
        }
        System.out.println(stockInfoService.infoRealTime(code).getCode());
        return stockInfoService.infoToday(code);
    }

    @GetMapping("/baseStock")
    public List<BaseStock> baseStock() throws IOException {
        List<BaseStock> baseStocks = stockInfoService.baseStock();
        return baseStocks;
    }

    @GetMapping("/selfStock")
    public List<PersonalStock> personalStock(@RequestParam("createId") Integer createId) throws IOException {
        System.out.println(createId);
        List<PersonalStock> personalStocks = stockInfoService.selfStock(createId);
        return personalStocks;
    }

    @PostMapping("/insertSelfStock")
    public void insertSelfStock(@RequestParam("createId") String createId, @RequestParam("stockId") String stockId){
        System.out.println("11"+createId+stockId);
        Integer uid = Integer.valueOf(createId);
        stockInfoService.insertSelfStock(uid,stockId);
    }

    @PostMapping("/deleteSelfStock")
    public void deleteSelfStock(@RequestParam("createId") String createId, @RequestParam("stockId") String stockId){
        Integer uid = Integer.valueOf(createId);
        stockInfoService.deleteSelfStock(uid,stockId);
    }
}
