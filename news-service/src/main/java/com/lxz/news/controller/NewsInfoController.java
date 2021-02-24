package com.lxz.news.controller;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/1/15/15:21
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.news.controller
 * 文件描述: @Description: 控制层
 *
 * All rights Reserved, Designed By 财富管理部
 * @Copyright:2016-2021
 *
 ********************************************************/

import com.lxz.news.entity.New;
import com.lxz.news.service.NewsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 包名称： com.lxz.news.controller
 * 类名称：NewsInfoController
 * 类描述：控制层
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/1/15/15:21
 */
@RestController
public class NewsInfoController {
    @Autowired
    NewsInfoService newsInfoService;

    /*
    社会 体育 娱乐 时尚
     */
    @GetMapping("/listNews")
    public List<New> listNews(@RequestParam String name){
        List<New> news = newsInfoService.listNewsInfo(name);
        return  news;
    }

    /*
    财经 军事 国际 科技
     */
    @GetMapping("/listClassifyNews")
    public List<New> listClassifyNews(@RequestParam String name){
        List<New> classifyNews = newsInfoService.listNewByClassify(name);
        return classifyNews;
    }

    /*
    头条
     */
    @GetMapping("/listNewsInfo")
    public  List<New> listNewsInfo(){
        List<New> news = newsInfoService.listNew();
        return news;
    }

}
