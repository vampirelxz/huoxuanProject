package com.lxz.stock.entity;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/3/12/14:09
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.stock.entity
 * 文件描述: @Description: 周比较
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import lombok.Data;

import java.io.Serializable;

/**
 * 包名称： com.lxz.stock.entity
 * 类名称：AccountWeekDate
 * 类描述：周比较
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/3/12/14:09
 */

@Data
public class AccountWeekDate implements Serializable {
    private static final long serialVersionUID = 8427448387784616010L;
    String value;
}
