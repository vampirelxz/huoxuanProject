package com.lxz.stock.entity;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/3/2/17:17
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.stock.entity
 * 文件描述: @Description: 查询基金、基金基础数据实体类
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import lombok.Data;

import java.io.Serializable;

/**
 * 包名称： com.lxz.stock.entity
 * 类名称：BaseFund
 * 类描述：查询基金、基金基础数据实体类
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/3/2/17:17
 */

@Data
public class BaseFund implements Serializable {
    private static final long serialVersionUID = 8427448387784616080L;
    String fund_units;

    String end_date;

    String in_or_out;

    String name;

    String name_short;

    String code;

    String type;

    String start_date;

    String fund_size;

    String pinyin;

    String manager;

    String company;

    String bank;

    String flow;
}
