package com.lxz.webui.consumer.controller;/****************************************************
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

import com.lxz.webui.consumer.api.feign.LifetoolsFeign;
import com.lxz.webui.entity.ToDoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 包名称： com.lxz.webui.consumer.controller
 * 类名称：ToDoListController
 * 类描述：调用每日清单接口
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/2/1/16:12
 */

@RestController
public class LifetoolsController {

    @Autowired(required = false)
    private LifetoolsFeign lifetoolsFeign;


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
    public List<ToDoList> feign(int uid){
        List<ToDoList> toDoLists;
        try {
            toDoLists = lifetoolsFeign.toDoList(uid);
        }catch (Exception e1){
            toDoLists=null;
            e1.printStackTrace();
        }
        return toDoLists;
    }

    @PostMapping("/saveInfo")
    public String save(@RequestParam("information") String information,@RequestParam("endTime") String endTime ,@RequestParam("createId") String createId){
        System.out.println(createId);
        System.out.println(endTime);
        System.out.println(information);
        endTime=endTime.replace('T',' ');
        endTime=endTime+":00";
        int uid=Integer.parseInt(createId);
        lifetoolsFeign.save(uid, information, endTime);
        return "localhost:80/index";
    }
}
