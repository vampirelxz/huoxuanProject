package com.lxz.forecast.entity;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/1/13/10:58
 * 项目名称：  huoxuan
 * 文件名称: com.lxz.forecast.entity
 * 文件描述: @Description: 从高德ip转地址实体类
 *
 * All rights Reserved, Designed By 财富管理部
 * @Copyright:2016-2021
 *
 ********************************************************/

import lombok.Data;

import java.io.Serializable;

/**
 * 包名称： com.lxz.forecast.entity
 * 类名称：
 * 类描述：从高德ip转地址实体类
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/1/13/10:58
 */

@Data
public class AmapPosition implements Serializable{
    private static final long serialVersionUID = -3786069459453021626L;

    String province;

    String city;

    int adcode;

    String rectangle;
}
