package com.lxz.user.controller;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/4/12/16:24
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.user.controller
 * 文件描述: @Description: 用户信息控制层
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import com.lxz.user.service.UserDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

/**
 * 包名称： com.lxz.user.controller
 * 类名称：UserDataController
 * 类描述：用户信息控制层
 * 创建人：@author xxxxx
 * 创建时间：2021/4/12/16:24
 */
@RestController
public class UserDataController {
    @Autowired
    UserDateService userDateService;

    @GetMapping("/onlineUser")
    int onlineUser(String token) throws ParseException {
        int i = userDateService.onlineUser(token);
        return i;
    }

    @GetMapping("/userTime")
    long useTime(String email){
        Long aLong = userDateService.useTime(email);
        return aLong;
    }

    @GetMapping("/visitUser")
    int visitUser(String token){
        int i = userDateService.visitUser(token);
        return i;
    }
}
