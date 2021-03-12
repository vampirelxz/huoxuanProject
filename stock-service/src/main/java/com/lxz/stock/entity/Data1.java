package com.lxz.stock.entity;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/3/11/17:21
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.stock.entity
 * 文件描述: @Description: 
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import lombok.Data;

import java.io.Serializable;

/**
 * 包名称： com.lxz.stock.entity
 * 类名称：Data1
 * 类描述：
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/3/11/17:21
 */
@Data
public class Data1 implements Serializable {
    private static final long serialVersionUID = 8427448387784616010L;
    String name;
    Float value;
}
