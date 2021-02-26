package com.lxz.stock.entity;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/2/26/16:57
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.stock.entity
 * 文件描述: @Description: 个人关注股票
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import lombok.Data;

import java.io.Serializable;

/**
 * 包名称： com.lxz.stock.entity
 * 类名称：PersonalStock
 * 类描述：个人关注股票
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/2/26/16:57
 */

@Data
public class PersonalStock implements Serializable {
    private static final long serialVersionUID = 8427448387784616080L;

    String name;

    String code;

    String nowPrice;

    /*
    换手率
     */
    String turnover;

    /*
    涨跌金额
     */
    String diff_money;

    /*
    涨跌幅度
     */
    String diff_rate;

    /*
    总市值
     */
    String all_value;

    /*
    流通市值
     */
    String circulation_value;

    /*
    总股本（万股）
     */
    String totalcapital;

    /*
    流通股本（万股）
     */
    String currcapital;

    /*
    市盈率
     */
    String pe;

    /*
    市净率
     */
    String pb;

}
