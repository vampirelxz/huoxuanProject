package com.lxz.lifetools.entity;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/3/16/15:53
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.lifetools.entity
 * 文件描述: @Description: 随手记实体类
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/


import lombok.Data;

import java.io.Serializable;

/**
 * 包名称： com.lxz.lifetools.entity
 * 类名称：Note
 * 类描述：随手记实体类
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/3/16/15:53
 */

@Data
public class Note implements Serializable {
    private static final long serialVersionUID = -1874347215015341154L;

    Integer id;

    Integer createId;

    String updateTime;

    String title;

    String content;
}
