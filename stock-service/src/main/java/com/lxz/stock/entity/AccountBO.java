package com.lxz.stock.entity;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/3/9/15:25
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.stock.entity
 * 文件描述: @Description: 账簿数据库实体类
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import lombok.Data;

import java.io.Serializable;

/**
 * 包名称： com.lxz.stock.entity
 * 类名称：AccountBO
 * 类描述：账簿数据库实体类
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/3/9/15:25
 */

@Data
public class AccountBO implements Serializable {
    private static final long serialVersionUID = 8427448387781116080L;

    Integer id;

    Integer createId;

    String type;

    String  time;

    String remark;

    Float money;
}
