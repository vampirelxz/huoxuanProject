package com.lxz.lifetools.service;

import com.lxz.lifetools.entity.AiReply;

/****************************************************
 * 创建人：     @author xxxxx    
 * 创建时间: 2021/3/22/9:58
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.lifetools.service
 * 文件描述: @Description: ${TODO}
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/
public interface AiReplyService {
    AiReply getReply(String question);
}
