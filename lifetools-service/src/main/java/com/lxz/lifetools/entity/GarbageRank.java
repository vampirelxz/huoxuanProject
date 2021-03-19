package com.lxz.lifetools.entity;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/3/19/9:53
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.lifetools.entity
 * 文件描述: @Description: 垃圾分类热搜排行榜
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import lombok.Data;

import java.io.Serializable;

/**
 * 包名称： com.lxz.lifetools.entity
 * 类名称：GarbageRank
 * 类描述：垃圾分类热搜排行榜
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/3/19/9:53
 */

@Data
public class GarbageRank implements Serializable {
    private static final long serialVersionUID = -1874347215115341154L;

    int index;

    String name;
}
