package com.lxz.lifetools.service.impl;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/2/7/16:14
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.lifetools.service.impl
 * 文件描述: @Description: 每日清单实现类
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import com.lxz.lifetools.dao.ToDoListMapper;
import com.lxz.lifetools.entity.ToDoList;
import com.lxz.lifetools.service.ToDoListService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 包名称： com.lxz.lifetools.service.impl
 * 类名称：ToDoListServiceImpl
 * 类描述：
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/2/7/16:14
 */
@Service
public class ToDoListServiceImpl implements ToDoListService {

    @Resource
    ToDoListMapper toDoListMapper;

    @Override
    public List<ToDoList> listToDoList(int createId){
       //sql的当前时间
        java.util.Date creatDate = new java.util.Date();
        java.sql.Date date = new java.sql.Date(creatDate.getTime());

        java.util.Date currentDate=new java.util.Date(System.currentTimeMillis());

//        System.out.println("当前时间："+date);
//        System.out.println("当前时间 ："+currentDate);
        //获取信息
        List<ToDoList> toDoLists = toDoListMapper.listToDoList(createId, currentDate);
        System.out.println(toDoLists);
        //添加属性duration
        toDoLists.forEach((toDoList ->
                toDoList.setDurationTime(toDoList.getEndTime().getTime()-date.getTime())
        ));
        System.out.println(toDoLists);
        if(toDoLists.isEmpty()){
            return null;
        }
        for(int i=0;i < toDoLists.size();i++) {
            long diff = toDoLists.get(i).getDurationTime();
            long days = diff / (1000 * 60 * 60 * 24);

            long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
            long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
            System.out.println("" + days + "天" + hours + "小时" + minutes + "分");
            toDoLists.get(i).setDurationTime(diff);
            toDoLists.get(i).setDuration("" + days + "天" + hours + "小时" + minutes + "分");
        }
        return toDoLists;
    }

    @Override
    public int save(String createId, String endTime, String information) {
        java.util.Date createTime = new java.util.Date();
        System.out.println("save"+createTime);
        int uid=Integer.parseInt(createId);
        int save = toDoListMapper.save(uid, endTime, createTime, information);
        return save;
    }
}
