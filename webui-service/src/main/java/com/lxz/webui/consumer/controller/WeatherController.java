package com.lxz.webui.consumer.controller;/****************************************************
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

import com.lxz.webui.consumer.api.feign.WeatherFeign;
import com.lxz.webui.entity.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 包名称： com.lxz.webui.consumer.controller
 * 类名称：WeatherController
 * 类描述：调用天气接口
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/2/1/16:12
 */

@RestController
//@CrossOrigin(origins="*",maxAge=3600)
public class WeatherController {

    @Autowired(required = false)
    private WeatherFeign weatherFeign;

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
    @RequestMapping("/weather")
    public Weather feign(){
        return weatherFeign.weather();
    }
}