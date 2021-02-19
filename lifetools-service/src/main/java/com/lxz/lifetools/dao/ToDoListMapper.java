package com.lxz.lifetools.dao;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/2/7/16:16
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.lifetools.dao
 * 文件描述: @Description: 每日清单数据层
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import com.lxz.lifetools.entity.ToDoList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 包名称： com.lxz.lifetools.dao
 * 类名称：ToDoListMapper
 * 类描述：每日清单数据层
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/2/7/16:16
 */
@Mapper
public interface ToDoListMapper {
    /**
     * @Title: listToDoList
     * @Description:  列举每日清单
     * @param createId   创建人ID
     * @param endTime 计划结束时间
     * @return:    List<ToDoList>
     * @throws
     * @author: liuxuanzhi
     * @Date:  2021/2/7/16:13
     */
    List<ToDoList> listToDoList(@Param("createId") int createId,@Param("endTime") Date endTime);

    /**
     * @Title: save
     * @Description:  添加每日清单数据
     * @param createId
     * @param endTime
     * @param createTime
     * @param information
     * @return:   List<ToDoList>
     * @throws
     * @author: liuxuanzhi
     * @Date:  2021/2/18/17:30
     */
    int save(@Param("createId") int createId,@Param("endTime")String endTime,@Param("createTime")Date createTime,@Param("information") String information);
}
