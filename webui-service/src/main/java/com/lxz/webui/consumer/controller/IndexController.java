package com.lxz.webui.consumer.controller;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/2/1/15:58
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.webui.consumer.controller
 * 文件描述: @Description: 启动控制层
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 包名称： com.lxz.webui.consumer.controller
 * 类名称：IndexController
 * 类描述：启动控制层
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/2/1/15:58
 */

@Controller
public class IndexController {
    @Autowired
    WeatherController weatherController;

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("weather",weatherController.feign());

        return "/index";
    }
}
