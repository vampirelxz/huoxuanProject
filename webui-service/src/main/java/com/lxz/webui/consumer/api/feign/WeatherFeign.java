package com.lxz.webui.consumer.api.feign;

import com.lxz.webui.entity.AlgorithmInfo;
import com.lxz.webui.entity.Weather;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/2/1/15:58
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.webui.consumer.api.feign
 * 文件描述: @Description: ${TODO}
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/
@Component
@FeignClient("forecast")
public interface WeatherFeign {
    @RequestMapping("/weather")
    Weather weather();

    @RequestMapping("/getCity")
    String getCity();

    @RequestMapping("/run")
    String runCode2();

    @RequestMapping("/listAlgorithmInfo")
    List<AlgorithmInfo> listAlgorithmInfo();

}
