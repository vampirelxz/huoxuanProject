package com.lxz.webui.controller;/****************************************************
 * 创建人：     @author xxxxx    
 * 创建时间: 2021/3/19/10:51
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.webui.controller
 * 文件描述: @Description: 垃圾分类控制层
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import com.lxz.webui.consumer.api.feign.LifetoolsFeign;
import com.lxz.webui.entity.Garbage;
import com.lxz.webui.utils.GarbageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 包名称： com.lxz.webui.controller
 * 类名称：GarbageController
 * 类描述：垃圾分类控制层
 * 创建人：@author xxxxx
 * 创建时间：2021/3/19/10:51
 */
@RestController
public class GarbageController  {
    @Autowired
    LifetoolsFeign lifetoolsFeign;
    @Autowired
    UtilController utilController;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    GarbageUtil garbageUtil;

    @GetMapping("/garbageRankTitil")
    public String[] garbageRankTitil() {
        String[] strings = lifetoolsFeign.garbageRankTitil();
        return strings;
    }

    @GetMapping("/garbageRankValue")
    public int[] garbageRankValue() {
        int[] ints = lifetoolsFeign.garbageRankValue();
        return ints;
    }

    @GetMapping("/garbage")
    public String listGarbage(@RequestParam("name") String name){
        String content;
        String civilized = garbageUtil.isCivilized(name);
        if(civilized.equals(name)){
            List<Garbage> garbage = lifetoolsFeign.garbage(name);
            String name1 = garbage.get(0).getName();
            String type = garbage.get(0).getType();
            String contain = garbage.get(0).getContain();
            String explain = garbage.get(0).getExplain();
            String tip = garbage.get(0).getTip();

            content="<div class=\"direct-chat-msg\" >\n" +
                    "                                    <!-- /.direct-chat-infos -->\n" +
                    "                                    <div class=\"direct-chat-infos clearfix\">\n" +
                    "                                        <span class=\"direct-chat-name float-left\">咨询助手</span>\n" +
                    "                                    </div>\n" +
                    "                                    <img class=\"direct-chat-img\" src=\"../dist/img/user3-128x128.jpg\" alt=\"Message User Image\">\n" +
                    "                                    <!-- /.direct-chat-img -->\n" +
                    "\n" +
                    "                                    <div class=\"direct-chat-text\" style=\"width: auto;max-width: 60%;float: left;margin-left: 2%;\">\n" +
                    "                                        "+name1+"是"+type+"。\n" +
                    "                                    </div>\n" +
                    "                                    <!-- /.direct-chat-text -->\n" +
                    "                                </div>\n" +
                    "                                <div class=\"direct-chat-msg\" >\n" +
                    "                                    <!-- /.direct-chat-infos -->\n" +
                    "                                    <div class=\"direct-chat-infos clearfix\">\n" +
                    "                                        <span class=\"direct-chat-name float-left\">咨询助手</span>\n" +
                    "                                    </div>\n" +
                    "                                    <img class=\"direct-chat-img\" src=\"../dist/img/user3-128x128.jpg\" alt=\"Message User Image\">\n" +
                    "                                    <!-- /.direct-chat-img -->\n" +
                    "\n" +
                    "                                    <div class=\"direct-chat-text\" style=\"width: auto;max-width: 60%;float: left;margin-left: 2%;\">\n" +
                    "                                        "+explain+" "+contain+","+tip+"。\n" +
                    "                                    </div>\n" +
                    "                                    <!-- /.direct-chat-text -->\n" +
                    "                                </div><div id=\"addMessage\"></div>";
        }else{
            content="<div class=\"direct-chat-msg\" >\n" +
                    "                                    <!-- /.direct-chat-infos -->\n" +
                    "                                    <div class=\"direct-chat-infos clearfix\">\n" +
                    "                                        <span class=\"direct-chat-name float-left\">咨询助手</span>\n" +
                    "                                    </div>\n" +
                    "                                    <img class=\"direct-chat-img\" src=\"../dist/img/user3-128x128.jpg\" alt=\"Message User Image\">\n" +
                    "                                    <!-- /.direct-chat-img -->\n" +
                    "\n" +
                    "                                    <div class=\"direct-chat-text\" style=\"width: auto;max-width: 60%;float: left;margin-left: 2%;\">\n" +
                    "                                        "+civilized+"\n" +
                    "                                    </div>\n" +
                    "                                    <!-- /.direct-chat-text -->\n" +
                    "                                </div><div id=\"addMessage\"></div>";

        }


        return content;
    }


}
