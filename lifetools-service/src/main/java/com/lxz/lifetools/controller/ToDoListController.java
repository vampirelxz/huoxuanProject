package com.lxz.lifetools.controller;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/2/8/11:02
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.lifetools.controller
 * 文件描述: @Description: 每日清单控制层
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import com.lxz.lifetools.entity.ToDoList;
import com.lxz.lifetools.service.ToDoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

/**
 * 包名称： com.lxz.lifetools.controller
 * 类名称：ToDoListController
 * 类描述：每日清单控制层
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/2/8/11:02
 */
@RestController
public class ToDoListController {

    @Autowired
    ToDoListService toDoListService;

    @GetMapping("/toDoList")
    public List<ToDoList> listToDoList(@RequestParam int createId){
        try {
            List<ToDoList> toDoLists = toDoListService.listToDoList(createId);
            System.out.println(toDoLists);
            return toDoLists;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
