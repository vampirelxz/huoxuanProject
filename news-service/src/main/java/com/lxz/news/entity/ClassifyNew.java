package com.lxz.news.entity;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/1/15/11:56
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.news.entity
 * 文件描述: @Description: 分类新闻的实体类
 *
 * All rights Reserved, Designed By 财富管理部
 * @Copyright:2016-2021
 *
 ********************************************************/

import lombok.Data;

import java.io.Serializable;

/**
 * 包名称： com.lxz.news.entity
 * 类名称：ClassifyNew
 * 类描述：分类新闻的实体类
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/1/15/11:56
 */

@Data
public class ClassifyNew implements Serializable {
    private static final long serialVersionUID = 5738620437707209605L;
    private Boolean havePic;
    private String pubDate;
    private String title;
    private String channelName;
    private String imageurls;
    private String source;
    private String channelId;
    private String link;
    private String img;
}
