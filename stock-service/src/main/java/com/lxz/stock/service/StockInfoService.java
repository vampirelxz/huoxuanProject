package com.lxz.stock.service;

import com.lxz.stock.entity.BuyStock;
import com.lxz.stock.entity.Stock;

/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/1/14/9:40
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.stock.service
 * 文件描述: @Description: ${TODO}
 *
 * All rights Reserved, Designed By 财富管理部
 * @Copyright:2016-2021
 *
 ********************************************************/
public interface StockInfoService {
    BuyStock infoRealTime(int code);

    Stock infoToday(int code);


}
