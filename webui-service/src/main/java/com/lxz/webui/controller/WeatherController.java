package com.lxz.webui.controller;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/2/1/16:12
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.webui.consumer.controller
 * 文件描述: @Description: 调用天气接口
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import com.lxz.webui.consumer.api.feign.GatewayFeign;
import com.lxz.webui.consumer.api.feign.WeatherFeign;
import com.lxz.webui.entity.Forecast;
import com.lxz.webui.entity.Weather;
import com.lxz.webui.utils.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 包名称： com.lxz.webui.consumer.controller
 * 类名称：WeatherController
 * 类描述：调用天气接口
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/2/1/16:12
 */

@RestController
public class WeatherController {

    @Autowired(required = false)
    private WeatherFeign weatherFeign;
    @Autowired
    GatewayFeign gatewayFeign;
    @Autowired
    IpUtil ipUtil;

/**
 * @Title:
 * @Description:
 *
 * @param
 * @return:
 * @throws
 * @author: liuxuanzhi
 * @Date:  2021/2/1/16:13
 */
    @GetMapping("/weather")
    public Weather feign(@RequestParam("ip") String ip){
        Weather weather = null;
        try {
            weather = weatherFeign.weather(ip);
        }catch (Exception e1){
            e1.printStackTrace();
        }
        return weather;
    }

    @RequestMapping("/getCity")
    public String getCity(){
        String city="长沙";
        String ip = ipUtil.getIp();
        try{
            city=weatherFeign.getCity(ip);
        }catch (Exception e1){
            e1.printStackTrace();
        }
        return city;
    }

    @RequestMapping("/forecast")
    public List<Forecast> getForecast(){
        String ip = ipUtil.getIp();
        List<Forecast> forecast = weatherFeign.getForecast(ip);
        return forecast;
    }
}
