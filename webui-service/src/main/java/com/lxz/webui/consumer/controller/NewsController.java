package com.lxz.webui.consumer.controller;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/2/23/9:17
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.webui.consumer.controller
 * 文件描述: @Description: 新闻控制层
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import com.lxz.webui.consumer.api.feign.NewsFeign;
import com.lxz.webui.entity.New;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 包名称： com.lxz.webui.consumer.controller
 * 类名称：NewsController
 * 类描述：新闻控制层
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/2/23/9:17
 */
@Controller
public class NewsController {
    @Autowired
    NewsFeign newsFeign;
/*
整个写法使用themyleaf的局部刷新方式
 */

    @GetMapping("/listNews")
    public String listNews(@RequestParam String name , Model model){
        List<New> news = newsFeign.listNews(name);
        model.addAttribute(name+"News",news);
        System.out.println(model.getAttribute(name+"News"));
        return  "news::"+name+"-tab";
    }

    @GetMapping("/listClassifyNews")
    public String listClassifyNews(@RequestParam String name, Model model){
        List<New> classifyNews = newsFeign.listClassifyNews(name);
        model.addAttribute(name+"News",classifyNews);
        System.out.println(model.getAttribute(name+"News"));
        return "news::"+name+"-tab";
    }

    @GetMapping("/listNewsInfo")
    public String listNewsInfo(Model model){
        List<New> news = newsFeign.listNewsInfo();
        model.addAttribute("news",news);
        return "news::toutiao-tab";
    }



}
