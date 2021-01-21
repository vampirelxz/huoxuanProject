package com.lxz.forecast.entity;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/1/21/13:23
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.forecast.entity
 * 文件描述: @Description: 当天天气实体类
 *
 * All rights Reserved, Designed By 财富管理部
 * @Copyright:2016-2021
 *
 ********************************************************/

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 包名称： com.lxz.forecast.entity
 * 类名称：Weather
 * 类描述：当天天气实体类
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/1/21/13:23
 */
@Component
@Data
public class    Weather implements Serializable {
    private static final long serialVersionUID = -7064941355288377451L;
    private String province;
    private String city;
    private int adcode;
    private int temperature;
    private String weather;
    private String winddirection;
    private String windpower;
    private int humidity;
    private String reporttime;

}
