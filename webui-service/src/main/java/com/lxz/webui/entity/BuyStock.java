package com.lxz.webui.entity;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/1/14/9:50
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.stock.entity
 * 文件描述: @Description: 股票实时分笔实体类
 *
 * All rights Reserved, Designed By 财富管理部
 * @Copyright:2016-2021
 *
 ********************************************************/

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

/**
 * 包名称： com.lxz.stock.entity
 * 类名称：Stock
 * 类描述：股票实体类
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/1/14/9:50
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class BuyStock implements Serializable {
    private static final long serialVersionUID = 8427448387784616080L;
    String code;
    /**
     * 股票名字
     */
    String name;
    /**
     * "今日开盘价",
     */
    float open;
    /**
     * "昨日收盘价",
     */
    float pre_close;
    /**
     *  "当前价格",
     */
    float price;
    /**
     * "今日最高价",
     */
    float high;
    /**
     * "今日最低价"
     */
    float low;
    /**
     * "竞买价，即买一报价",
     */
    float bid;
    /**
     * "竞卖价，即卖一报价",
     */
    float ask;
    /**
     * "成交量",
     */
    int volume;
    /**
     * "成交金额（元 CNY）",
     */
    float amount;
    /**
     * 时间
     */
    String time;
    /**
     *委买一（笔数 bid volume）",
     */
    int b1_v;
    /**
     * "委买一（价格 bid price）",
     */
    float b1_p;
    /**
     *委买2（笔数 bid volume）",
     */
    int b2_v;
    /**
     * "委买2（价格 bid price）",
     */
    float b2_p;
    /**
     *委买3（笔数 bid volume）",
     */
    int b3_v;
    /**
     * "委买3（价格 bid price）",
     */
    float b3_p;
    /**
     *委买4（笔数 bid volume）",
     */
    int b4_v;
    /**
     * "委买4（价格 bid price）",
     */
    float b4_p;
    /**
     *委买5（笔数 bid volume）",
     */
    int b5_v;
    /**
     * "委买5（价格 bid price）",
     */
    float b5_p;
    /**
     * "委卖一（笔数 ask volume）",
     */
    int a1_v;
    /**
     *  "委卖一（价格 ask price）",
     */
    float a1_p;
    /**
     * "委卖2（笔数 ask volume）",
     */
    int a2_v;
    /**
     *  "委卖2（价格 ask price）",
     */
    float a2_p;
    /**
     * "委卖3（笔数 ask volume）",
     */
    int a3_v;
    /**
     *  "委卖3（价格 ask price）",
     */
    float a3_p;
    /**
     * "委卖4（笔数 ask volume）",
     */
    int a4_v;
    /**
     *  "委卖4（价格 ask price）",
     */
    float a4_p;
    /**
     * "委卖5（笔数 ask volume）",
     */
    int a5_v;
    /**
     *  "委卖5（价格 ask price）",
     */
    float a5_p;
}
