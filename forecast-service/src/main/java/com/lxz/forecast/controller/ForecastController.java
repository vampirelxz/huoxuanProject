package com.lxz.forecast.controller;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/1/12/13:49
 * 项目名称：  huoxuan
 * 文件名称: com.lxz.forecast.controller
 * 文件描述: @Description: 天气预报控制层
 *
 * All rights Reserved, Designed By 财富管理部
 * @Copyright:2016-2021
 *
 ********************************************************/

import com.lxz.forecast.entity.Forecast;
import com.lxz.forecast.entity.Weather;
import com.lxz.forecast.service.impl.WeatherServiceImpl;
import com.lxz.forecast.utils.PositionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 包名称： com.lxz.forecast.controller
 * 类名称：ForecastController
 * 类描述：天气预报控制层
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/1/12/13:49
 */
//@CrossOrigin(origins="*",maxAge=3600)
@RestController
public class ForecastController {


    @Autowired
    WeatherServiceImpl weatherService;

    @Value("${amap.forecast.key}")
    private String key;

    @Autowired
    PositionUtil positionUtil;


    @GetMapping("/forecast")
    public List<Forecast> getForecast(String ip){
        String city=positionUtil.getCity(ip);
        return weatherService.getForecast(city);
    }

    @GetMapping("/weather")
    public Weather getWeather(String ip){
        String city=positionUtil.getCity(ip);
        System.out.println(city);
        return weatherService.getWeather(city);
    }

}
