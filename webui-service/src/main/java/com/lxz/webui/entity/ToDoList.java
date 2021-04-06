package com.lxz.webui.entity;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/2/7/15:54
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.lifetools.entity
 * 文件描述: @Description: 每日清单实体类
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 包名称： com.lxz.lifetools.entity
 * 类名称：ToDoList
 * 类描述：每日清单实体类
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/2/7/15:54
 */

@Data
@NoArgsConstructor
public class ToDoList implements Serializable {
    private static final long serialVersionUID = -1874347215095341154L;
    private int id;
    private String information;
    private Timestamp endTime;
    private Long durationTime;
    private String duration;
}
