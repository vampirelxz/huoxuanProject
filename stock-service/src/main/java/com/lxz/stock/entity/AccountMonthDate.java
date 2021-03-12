package com.lxz.stock.entity;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/3/12/17:14
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.stock.entity
 * 文件描述: @Description: 月比较实体类
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import lombok.Data;

import java.io.Serializable;

/**
 * 包名称： com.lxz.stock.entity
 * 类名称：AccountMonthDate
 * 类描述：月比较实体类
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/3/12/17:14
 */

@Data
public class AccountMonthDate implements Serializable {
    private static final long serialVersionUID = 8427448387784616010L;
    String value;
}
