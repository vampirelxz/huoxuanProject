package com.lxz.webui.entity;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/3/2/17:28
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.stock.entity
 * 文件描述: @Description: 关注基金实体类
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import lombok.Data;

import java.io.Serializable;

/**
 * 包名称： com.lxz.stock.entity
 * 类名称：PersonalFund
 * 类描述：关注基金实体类
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/3/2/17:28
 */

@Data
public class PersonalFund implements Serializable {
    private static final long serialVersionUID = 8427448387784616080L;
    String name;

    String todayMax;

    /**
    成交量
     */
    String tradeNum;

    /**
    状态  1正常 -1停牌
     */
    String state;

    /**
    开盘价
     */
    String openPrice;

    String date;

    /**
    收盘价
     */
    String closePrice;

    String time;

    /**
    成交额
     */
    String tradeAmount;

    /**
    委比
     */
    String appointRate;

    /**
    涨跌幅
     */
    float diff_rate;

    /**
    委差
     */
    String appointDiff;

    /**
    昨日收盘价
     */
    String yestodayClosePrice;

    /**
    今日最低
     */
    String todayMin;

    /**
    涨跌金额
     */
    String diff_money;

    /**
    基金代码
     */
    String code;

    /**
    当前价格
     */
    String nowPrice;

    /**
    净值
     */
    String dwjz;

    /**
    估算净值
     */
    String gsz;

    /**
    估算涨跌幅
     */
    float gszzl;

    String buy1_m;

    String buy1_n;

    String sell1_m;

    String sell1_n;

    String buy2_m;

    String buy2_n;

    String sell2_m;

    String sell2_n;

    String buy3_m;

    String buy3_n;

    String sell3_m;

    String sell3_n;

    String buy4_m;

    String buy4_n;

    String sell4_m;

    String sell4_n;

    String buy5_m;

    String buy5_n;

    String sell5_m;

    String sell5_n;



}
