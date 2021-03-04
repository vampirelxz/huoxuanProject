package com.lxz.stock.entity;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/2/26/17:25
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
 * 类名称：
 * 类描述：
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/2/26/17:25
 */
@Data
public class FundBO implements Serializable {
    private static final long serialVersionUID = 8427448337784616080L;

    int id;

    int deleteFlag;

    String fundId;

    int createId;
}
