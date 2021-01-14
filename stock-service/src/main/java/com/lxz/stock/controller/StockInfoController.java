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

import com.lxz.stock.entity.BuyStock;
import com.lxz.stock.entity.Stock;
import com.lxz.stock.service.impl.StockInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
    StockInfoServiceImpl stockInfoService;

    @GetMapping("/realtimeStock")
    public BuyStock listRealTimeStock(Integer code){
        if (code == null){
            code=300377;
        }
        System.out.println(stockInfoService.infoRealTime(code).getCode());
        return stockInfoService.infoRealTime(code);
    }

    @GetMapping("/todayStock")
    public  Stock listTodayStock(Integer code){
        if(code == null){
            code=300377;
        }
        System.out.println(stockInfoService.infoRealTime(code).getCode());
        return stockInfoService.infoToday(code);
    }
}
