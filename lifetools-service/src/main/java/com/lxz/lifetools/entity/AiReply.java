package com.lxz.lifetools.entity;/****************************************************
 * 创建人：     @author xxxxx    
 * 创建时间: 2021/3/22/9:57
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.lifetools.entity
 * 文件描述: @Description: 百度智能回复
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import lombok.Data;

import java.io.Serializable;

/**
 * 包名称： com.lxz.lifetools.entity
 * 类名称：AiReply
 * 类描述：百度智能回复
 * 创建人：@author xxxxx
 * 创建时间：2021/3/22/9:57
 */

@Data
public class AiReply implements Serializable {
    private static final long serialVersionUID = -2272405814509452028L;
    String content;
}
