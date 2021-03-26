package com.lxz.forecast.entity;/****************************************************
 * 创建人：     @author xxxxx    
 * 创建时间: 2021/3/26/13:34
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.forecast.entity
 * 文件描述: @Description: 用户算法记录实体类
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import lombok.Data;

import java.io.Serializable;

/**
 * 包名称： com.lxz.forecast.entity
 * 类名称：AlgorithmUser
 * 类描述：用户算法记录实体类
 * 创建人：@author xxxxx
 * 创建时间：2021/3/26/13:34
 */
@Data
public class AlgorithmUser implements Serializable {
    private static final long serialVersionUID = -3786061459413021626L;
    String id;
    String userId;
    String algorithmId;
    String content;
    String updateDate;
    String timeExpend;
    String spaceExpend;
}
