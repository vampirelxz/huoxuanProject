package com.lxz.webui.entity;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/3/11/14:00
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.stock.entity
 * 文件描述: @Description: 周信息
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import lombok.Data;

import java.io.Serializable;

/**
 * 包名称： com.lxz.stock.entity
 * 类名称：AccountWeekVO
 * 类描述：周信息
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/3/11/14:00
 */

@Data
public class AccountWeekVO implements Serializable {
    private static final long serialVersionUID = 8427448387784616010L;
    Float typeAll;

    Float weekAll;

    String type;

    Integer proportion;
}
