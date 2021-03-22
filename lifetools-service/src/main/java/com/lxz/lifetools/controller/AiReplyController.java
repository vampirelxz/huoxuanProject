package com.lxz.lifetools.controller;/****************************************************
 * 创建人：     @author xxxxx    
 * 创建时间: 2021/3/22/10:26
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.lifetools.controller
 * 文件描述: @Description: 智能回复控制层
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import com.lxz.lifetools.entity.AiReply;
import com.lxz.lifetools.service.AiReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 包名称： com.lxz.lifetools.controller
 * 类名称：AiReplyController
 * 类描述：智能回复控制层
 * 创建人：@author xxxxx
 * 创建时间：2021/3/22/10:26
 */
@RestController
public class AiReplyController {
    @Autowired
    AiReplyService aiReplyService;

    @GetMapping("/getAiReply")
    public String getAiReply(@RequestParam("question") String question){
        AiReply reply = aiReplyService.getReply(question);
        return reply.getContent();
    }
}
