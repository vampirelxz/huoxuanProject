package com.lxz.lifetools.service;

import com.lxz.lifetools.entity.ToDoList;

import java.text.ParseException;
import java.util.List;

/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/2/7/16:10
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.lifetools.service
 * 文件描述: @Description: ${TODO}
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/
public interface ToDoListService {
    /**
     * @Title: listToDoList
     * @Description: 列举每日清单
     * @param createId   创建人ID
     * @return:    int
     * @throws
     * @author: liuxuanzhi
     * @Date:  2021/2/7/16:13
     */
    List<ToDoList> listToDoList(int createId) throws ParseException;

    int save (int createId, String endTime, String information );
}
