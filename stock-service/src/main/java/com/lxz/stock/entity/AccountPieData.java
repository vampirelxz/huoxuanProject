package com.lxz.stock.entity;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/3/12/17:43
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.stock.entity
 * 文件描述: @Description: 饼图数据
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import lombok.Data;

import java.io.Serializable;

/**
 * 包名称： com.lxz.stock.entity
 * 类名称：AccountPieData
 * 类描述：饼图数据
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/3/12/17:43
 */

@Data
public class AccountPieData implements Serializable {
    private static final long serialVersionUID = 8427448387184616010L;
    String type;
    String value;
}
