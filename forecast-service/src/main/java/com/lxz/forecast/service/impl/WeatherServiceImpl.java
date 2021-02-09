package com.lxz.forecast.service.impl;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/1/21/13:33
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.forecast.service.impl
 * 文件描述: @Description: 天气服务实现类
 *
 * All rights Reserved, Designed By 财富管理部
 * @Copyright:2016-2021
 *
 ********************************************************/

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lxz.forecast.entity.Forecast;
import com.lxz.forecast.entity.Weather;
import com.lxz.forecast.service.WeatherService;
import com.lxz.forecast.utils.JsonUtil;
import com.lxz.forecast.utils.PositionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 包名称： com.lxz.forecast.service.impl
 * 类名称：WeatherServiceImpl
 * 类描述：天气服务实现类
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/1/21/13:33
 */
@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PositionUtil positionUtil;

    @Autowired
    Forecast forecast;

    @Autowired
    JsonUtil jsonUtil;

    @Value("${amap.forecast.key}")
    private String key;

    @Override
    public Weather getWeather() {
        String url="https://restapi.amap.com/v3/weather/weatherInfo?city="+positionUtil.getCity()+"&key="+key+"&extensions=base";
        //String json =restTemplate.getForObject(url,Object.class);
        ResponseEntity<String> results = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        JSONArray lives = JSONObject.parseObject(results.getBody()).getJSONArray("lives");
        return jsonUtil.jsonToObject(lives,Weather.class);
    }

    @Override
    public List<Forecast> getForecast() {
        String url="https://restapi.amap.com/v3/weather/weatherInfo?city="+positionUtil.getCity()+"&key="+key+"&extensions=all";
        //String json =restTemplate.getForObject(url,Object.class);
        ResponseEntity<String> results = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        String json = results.getBody();
        JSONObject jsonObject = JSONObject.parseObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray("forecasts").getJSONObject(0).getJSONArray("casts");
        List<Forecast> forecastList = new ArrayList<>();

        for (Iterator iterator = jsonArray.iterator(); iterator.hasNext(); ) {
            JSONObject jsonObject1 = (JSONObject) iterator.next();

            Forecast forecast1 = jsonObject1.toJavaObject(Forecast.class);
            forecast1.setCity(jsonObject.getJSONArray("forecasts").getJSONObject(0).getString("city"));
            forecast1.setAdcode(jsonObject.getJSONArray("forecasts").getJSONObject(0).getInteger("adcode"));
            forecast1.setProvince(jsonObject.getJSONArray("forecasts").getJSONObject(0).getString("province"));
            forecastList.add(forecast1);
        }
        return forecastList;
    }
}
