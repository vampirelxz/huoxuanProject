package com.lxz.webui.controller;/****************************************************
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

import com.lxz.webui.consumer.api.feign.LifetoolsFeign;
import com.lxz.webui.entity.ToDoList;
import com.lxz.webui.entity.Weather;
import com.lxz.webui.utils.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private LifetoolsFeign lifetoolsFeign;

    @Autowired
    IpUtil ipUtil;

    private int iuid=0;

    @RequestMapping("/")
    public String login(){
        return "/login";
    }


    @RequestMapping("/index")
    public String index(Model model){
        Weather weather=null;
        List<ToDoList> toDoLists=null;
        try {
            String ip = ipUtil.getIp();
            weather = weatherController.feign(ip);
        }catch (Exception e){
            e.printStackTrace();
            weather = null;
        }
        if(weather != null) {
            model.addAttribute("weather", weather);
        }

        return "/index.html";
    }


    @ResponseBody
    @GetMapping("/getUid/{uid}")
    public void getUid(@PathVariable int uid){
        System.out.println(uid);
        iuid=uid;
    }

    @ResponseBody
    @GetMapping("/getAiReply")
    public String getAiReply(@RequestParam("question")String question){
        String aiReply = lifetoolsFeign.getAiReply(question);
        String html ="<div class=\"direct-chat-msg\" >\n" +
                "                                    <!-- /.direct-chat-infos -->\n" +
                "                                    <div class=\"direct-chat-infos clearfix\">\n" +
                "                                        <span class=\"direct-chat-name float-left\">智能助手</span>\n" +
                "                                    </div>\n" +
                "                                    <img class=\"direct-chat-img\" src=\"../dist/img/user3-128x128.jpg\" alt=\"Message User Image\">\n" +
                "                                    <!-- /.direct-chat-img -->\n" +
                "\n" +
                "                                    <div class=\"direct-chat-text\" style=\"width: auto;max-width: 60%;float: left;margin-left: 2%;\">\n" +
                "                                        "+aiReply+"\n" +
                "                                    </div>\n" +
                "                                    <!-- /.direct-chat-text -->\n" +
                "                                </div><div id=\"addMessage\"></div>";
        return html;
    }
}
