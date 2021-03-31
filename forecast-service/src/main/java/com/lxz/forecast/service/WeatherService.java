package com.lxz.forecast.service;

import com.lxz.forecast.entity.Forecast;
import com.lxz.forecast.entity.Weather;
import org.springframework.stereotype.Service;

import java.util.List;

/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/1/21/13:31
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.forecast.service
 * 文件描述: @Description: ${TODO}
 *
 * All rights Reserved, Designed By 财富管理部
 * @Copyright:2016-2021
 *
 ********************************************************/

@Service
public interface WeatherService {
    Weather getWeather(String city);

    List<Forecast> getForecast(String city);
}
