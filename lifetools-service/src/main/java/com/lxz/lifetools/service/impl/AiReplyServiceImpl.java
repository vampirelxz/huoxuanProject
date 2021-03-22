package com.lxz.lifetools.service.impl;/****************************************************
 * 创建人：     @author xxxxx    
 * 创建时间: 2021/3/22/10:00
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.lifetools.service.impl
 * 文件描述: @Description: 智能回复
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import com.alibaba.fastjson.JSONObject;
import com.baidubce.http.ApiExplorerClient;
import com.baidubce.http.AppSigner;
import com.baidubce.http.HttpMethodName;
import com.baidubce.model.ApiExplorerRequest;
import com.baidubce.model.ApiExplorerResponse;
import com.lxz.lifetools.entity.AiReply;
import com.lxz.lifetools.service.AiReplyService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 包名称： com.lxz.lifetools.service.impl
 * 类名称：AiReplyService
 * 类描述：智能回复
 * 创建人：@author xxxxx
 * 创建时间：2021/3/22/10:00
 */
@Service
public class AiReplyServiceImpl implements AiReplyService {
    @Value("${api.baidu.AccessKey}")
    String accessKey;
    @Value("${api.baidu.appSecret}")
    String appSecret;
    @Override
    public AiReply getReply(String question) {
        String path = "https://jisuapiareacode.api.bdymkt.com/iqa/query";
        ApiExplorerRequest request = new ApiExplorerRequest(HttpMethodName.POST, path);
        request.setCredentials(accessKey, appSecret);

        request.addHeaderParameter("Content-Type", "application/json;charset=UTF-8");

        // 设置query参数
        request.addQueryParameter("question", question);


        ApiExplorerClient client = new ApiExplorerClient(new AppSigner());
        AiReply aiReply = new AiReply();
        try {
            ApiExplorerResponse response = client.sendRequest(request);

            // 返回结果格式为Json字符串
            System.out.println(response.getResult());
            String string = JSONObject.parseObject(response.getResult()).getJSONObject("result").getString("content");


            aiReply.setContent(string);

        } catch (Exception e) {
            e.printStackTrace();
            aiReply.setContent("error");
        }
        return aiReply;
    }

}
