package com.lxz.webui.controller;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/2/25/12:00
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.lifetools.controller
 * 文件描述: @Description: 工具控制层
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import com.lxz.webui.consumer.api.feign.UpdateUserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 包名称： com.lxz.lifetools.controller
 * 类名称：UtilController
 * 类描述：工具控制层
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/2/25/12:00
 */

@RestController
public class UtilController {
    @Autowired
    UpdateUserFeign updateUserFeign;

    public String t=null;

    /*
    获取token
     */
    @RequestMapping(value = "/getToken" ,method = RequestMethod.GET)
    public String getToken(@RequestParam("token") String token){
        System.out.println(token);
        t=token;
        return t;
    }

    /**
     * 刷新token
     * @param refreshToken
     * @return
     */
    @RequestMapping(value = "/refreshToken",method = RequestMethod.GET)
    public String refreshToken(@RequestParam("refreshToken") String refreshToken){
        Map<String, Object> stringObjectMap = updateUserFeign.refreshToken(refreshToken);
        String data = stringObjectMap.get("data").toString();
        return data;
    }


}
