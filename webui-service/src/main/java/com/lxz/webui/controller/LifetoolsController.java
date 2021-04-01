package com.lxz.webui.controller;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/2/1/16:12
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.webui.consumer.controller
 * 文件描述: @Description: 调用每日清单接口
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import com.lxz.webui.consumer.api.feign.GatewayFeign;
import com.lxz.webui.entity.ToDoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 包名称： com.lxz.webui.consumer.controller
 * 类名称：ToDoListController
 * 类描述：调用每日清单接口
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/2/1/16:12
 */

@Controller
public class LifetoolsController {

    @Autowired
    GatewayFeign gatewayFeign;

    @GetMapping("/listToDoList")
    public String listToDoList(@RequestParam("createId") int uid, @RequestParam("token") String token, Model model){
        List<ToDoList> toDoLists;
        try {
            toDoLists = gatewayFeign.toDoList(uid,token);
            if(toDoLists != null) {
                model.addAttribute("toDoList", toDoLists);
            }else{
                ToDoList toDoList = new ToDoList();
                toDoList.setId(0);
                toDoList.setInformation("今日暂无安排");
                long defaultTime=100000;
                toDoList.setDurationTime(defaultTime);
                model.addAttribute("toDoList",toDoList);
            }
        }catch (Exception e1){
            e1.printStackTrace();
        }
        return "index::todo-list";
    }

    @PostMapping("/saveInfo")
    @ResponseBody
    public void save(@RequestParam("information") String information,@RequestParam("endTime") String endTime ,@RequestParam("createId") String createId,@RequestParam("token") String token){
        endTime=endTime.replace('T',' ');
        endTime=endTime+":00";
        try{
            gatewayFeign.save(information, endTime, createId, token);
        }catch (Exception e){
            e.printStackTrace();

        }
    }


}
