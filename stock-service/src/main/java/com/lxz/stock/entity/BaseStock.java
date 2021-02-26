package com.lxz.stock.entity;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/2/26/13:26
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.stock.entity
 * 文件描述: @Description: 股票指数
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import lombok.Data;

import java.io.Serializable;

/**
 * 包名称： com.lxz.stock.entity
 * 类名称：BaseStock
 * 类描述：股票指数
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/2/26/13:26
 */

@Data
public class BaseStock implements Serializable {
    private static final long serialVersionUID = 8427448387784616080L;
    String tradeNum;

    String todayMax;

    String swing;

    String yestodayClosePrice;

    String time;

    String tradeAmount;

    String todayOpenPrice;

    String diff_money;

    String minPrice;

    String name;

    String diff_rate;

    String min52;

    String nowPrice;

    String code;

    String todayMin;

    String maxPrice;

    String max52;

    String market;

}
