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

import com.lxz.webui.entity.ToDoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
    @Autowired
    LifetoolsController lifetoolsController;

    int iuid=0;

    @RequestMapping("/")
    public String login(){
        return "/login";
    }

    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("weather",weatherController.feign());
        model.addAttribute("toDoList",toDoListfeign(iuid));
        return "/index";
    }


    public List<ToDoList> toDoListfeign(int uid){
        return lifetoolsController.feign(uid);
    }

    @ResponseBody
    @GetMapping("/getUid/{uid}")
    public void getUid(@PathVariable int uid){
        System.out.println(uid);
        iuid=uid;
    }
}
