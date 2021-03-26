package com.lxz.forecast.entity;/****************************************************
 * 创建人：     @author xxxxx    
 * 创建时间: 2021/3/26/13:30
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.forecast.entity
 * 文件描述: @Description: 算法题目实体类
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import lombok.Data;

import java.io.Serializable;

/**
 * 包名称： com.lxz.forecast.entity
 * 类名称：AlgorithmInfo
 * 类描述：算法题目实体类
 * 创建人：@author xxxxx
 * 创建时间：2021/3/26/13:30
 */
@Data
public class AlgorithmInfo implements Serializable {
    private static final long serialVersionUID = -3786061459453021626L;
    String id;
    String content1;
    String content2;
    String content3;
    String question;
    String thinking1;
    String thinking2;
    String thinking3;
    String type1;
    String type2;
    String type3;
    String title;
}
