package com.lxz.forecast.entity;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/1/21/13:27
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.forecast.entity
 * 文件描述: @Description: 天气预报实体类
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
 * 类名称：Forecast
 * 类描述：天气预报实体类
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/1/21/13:27
 */

@Component
@Data
public class Forecast implements Serializable {
    private static final long serialVersionUID = -7321513953165384515L;

    private String province;
    private String city;
    private int adcode;
    private String date;
    private int week;
    private String dayweather;
    private String nightweather;
    private int daytemp;
    private int nighttemp;
    private String datweind;
    private String nightwind;
    private String daypower;
    private String nightpower;
}
