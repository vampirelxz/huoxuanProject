package com.lxz.webui.controller;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/4/12/17:44
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.webui.controller
 * 文件描述: @Description: 用户数据统计控制类
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import com.lxz.webui.consumer.api.feign.GatewayFeign;
import com.lxz.webui.consumer.api.feign.UpdateUserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

/**
 * 包名称： com.lxz.webui.controller
 * 类名称：UserDataController
 * 类描述：用户数据统计控制类
 * 创建人：@author xxxxx
 * 创建时间：2021/4/12/17:44
 */
@RestController
public class UserDataController {
    @Autowired
    UpdateUserFeign updateUserFeign;
    @Autowired
    GatewayFeign gatewayFeign;

    @GetMapping("/onlineUser")
    int onlineUser(String token) throws ParseException {
        int i = updateUserFeign.onlineUser(token);
        return i;
    }

    @GetMapping("/userTime")
    @ResponseBody
    long useTime(@RequestParam("email") String email,@RequestParam("token")String token){
        long aLong = gatewayFeign.userTime(email,token);
        return aLong;
    }

    @GetMapping("/visitUser")
    int visitUser(String token){
        int i = updateUserFeign.visitUser(token);
        return i;
    }
}
