package com.lxz.news.entity;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/1/15/11:56
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.news.entity
 * 文件描述: @Description: 综合新闻数据的实体类
 *
 * All rights Reserved, Designed By 财富管理部
 * @Copyright:2016-2021
 *
 ********************************************************/

import lombok.Data;

import java.io.Serializable;

/**
 * 包名称： com.lxz.news.entity
 * 类名称：New
 * 类描述：综合新闻数据的实体类
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/1/15/11:56
 */
@Data
public class New implements Serializable {
    private static final long serialVersionUID = 4974363266945850101L;
    private String title;
    private String date;
    private String category;
    private String author_name;
    private String url;
    private String thumbnail_pic_s;
    private String thumbnail_pic_s02;
    private String thumbnail_pic_s03;
}
