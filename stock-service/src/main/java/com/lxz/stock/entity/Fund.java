package com.lxz.stock.entity;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/3/2/17:43
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.stock.entity
 * 文件描述: @Description: 基金排行实体类
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import lombok.Data;
import java.io.Serializable;

/**
 * 包名称： com.lxz.stock.entity
 * 类名称：Fund
 * 类描述：基金排行实体类
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/3/2/17:43
 */

@Data
public class Fund implements Serializable {
    private static final long serialVersionUID = 8427448387781616080L;

    /**
    3年增长%
     */
    String year3_inc;

    /**
    日增长 %(非货币型基金)
     */
    String day_inc;

    String in_or_out;

    /**
    万份收益（货币型基金）
     */
    String tt_profit;

    /**
    管理人
     */
    String manger;

    /**
    2年增长%
     */
    String year2_inc;

    /**
    名字简写
     */
    String name_short;

    /**
    单位净值(元/份)(非货币型基金)若返回的数字为整形0表示没有这个数据，若为浮点型表示这个数据为0
     */
    String unit_net_value;

    /**
    状态，1为正常交易，-1为退市
     */
    String state;

    /**
    累计净值(元/份)(非货币型基金)
     */
    String acc_net_value;

    String code;

    /**
    今年增长%
     */
    String now_year_inc;

    /**
    1年增长%
     */
    String year1_inc;

    /**
    基金类型
     */
    String type;

    /**
    3月增长%
     */
     String month3_inc;

    /**
     4周年化收益率（货币型基金）
      */
    String day28_year;

    /**
      周增长%
       */
    String week_inc;

    /**
      开始年份增长率
       */
    String begin_year_inc;

    /**
      名字
       */
     String name;

    /**
     月增长%
      */
     String month_inc;

    /**
     两周年化收益率（货币型基金）
      */
     String day14_year;

    /**
     1周年化收益率（货币型基金）
      */
     String day7_year;

    /**
     6月增长%
      */
     String month6_inc;


     String data_date;

}
