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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;


/**
 * 包名称： com.lxz.forecast.controller
 * 类名称：ForecastController
 * 类描述：天气预报控制层
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/1/12/13:49
 */

@RestController
public class ForecastController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PositionUtil positionUtil;

    @Autowired
    WeatherServiceImpl weatherService;

    @Value("${amap.forecast.key}")
    private String key;

    @CrossOrigin
    @GetMapping("/forecast")
    public List<Forecast> getForecast(){

//        String url="https://restapi.amap.com/v3/weather/weatherInfo?city="+positionUtil.getCity()+"&key="+key+"&extensions=all";
//        System.out.println(url);
//        //String json =restTemplate.getForObject(url,Object.class);
//
//        ResponseEntity<String> results = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
//        String json = results.getBody();
//        return json;
        return weatherService.getForecast();
    }

    @GetMapping("/weather")
    public Weather getWeather(){
//        String url="https://restapi.amap.com/v3/weather/weatherInfo?city="+positionUtil.getCity()+"&key="+key+"&extensions=base";
//        //String json =restTemplate.getForObject(url,Object.class);
//        ResponseEntity<String> results = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
//        String json = results.getBody();
//        return json;
        return weatherService.getWeather();
    }


//    @PostMapping(value = "/testPost")
//    public Object postJson(@RequestBody JSONObject param) {
//        System.out.println(param.toJSONString());
//        param.put("action", "post");
//        param.put("username", "tester");
//        param.put("pwd", "123456748");
//        return param;
//    }
//
//    @PostMapping(value = "/forecast")
//    public Object Forecast() {
//        String url = "https://restapi.amap.com/v3/weather/weatherInfo";
//        JSONObject postData = new JSONObject();
//        postData.put("city", "110101");
//        postData.put("key", key);
//        JSONObject json = restTemplate.postForEntity(url, postData, JSONObject.class).getBody();
//        return json;

//    }
}
