package com.lxz.webui.entity;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/1/14/10:42
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.stock.entity
 * 文件描述: @Description: 股票实时数据类
 *
 * All rights Reserved, Designed By 财富管理部
 * @Copyright:2016-2021
 *
 ********************************************************/

import lombok.Data;

import java.io.Serializable;

/**
 * 包名称： com.lxz.stock.entity
 * 类名称：Stock
 * 类描述：股票实时数据类
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/1/14/10:42
 */

@Data
public class Stock implements Serializable {
    private static final long serialVersionUID = 8427448387784616080L;
    String symbol;
    /**
     * 编号
     */
    int code;
    /**
     * 股票名字
     */
    String name;
    /**
     * 交易价格
     */
    float trade;
    /**
     * 价格变动
     */
    float pricechange;
    /**
     * 变动指数
     */
    float changepercent;
    /**
     * 买入价格
     */
    float buy;
    /**
     * 卖出价格
     */
    float sell;
    /**
     * 昨日收盘价
     */
    float settlement;
    /**
     * 开盘价
     */
    float open;
    /**
     * 最高
     */
    float high;
    /**
     * 最低
     */
    float low;
    /**
     * 成交额
     */
    float volume;
    /**
     * 成交数量
     */
    int amount;
    /**
     * 时间
     */
    String ticktime;
    /**
     * 市盈率
     */
    float per;
    /**
     * 市净率
     */
    float pb;
    /**
     * 市场价值
     */
    float mktcap;
    /**
     * 流通市值
     */
    float nmc;
    /**
     * 翻车率
     */
    float turnoverratio;
}
