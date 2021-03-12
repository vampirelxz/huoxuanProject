package com.lxz.webui.entity;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/3/11/11:05
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.stock.entity
 * 文件描述: @Description: 月模块VO
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import lombok.Data;

import java.io.Serializable;

/**
 * 包名称： com.lxz.stock.entity
 * 类名称：AccountMonthVO
 * 类描述：月模块VO
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/3/11/11:05
 */

@Data
public class AccountMonthVO implements Serializable {
    private static final long serialVersionUID = 8427448387784616010L;
    Float monthOut;

    Float monthIn;

    Float allIn;

    Float allOut;

    Float balance;

}
