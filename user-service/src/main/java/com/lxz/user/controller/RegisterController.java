package com.lxz.user.controller;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/4/6/10:52
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.user.controller
 * 文件描述: @Description: 注册控制层
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

/**
 * 包名称： com.lxz.user.controller
 * 类名称：RegisterController
 * 类描述：注册控制层
 * 创建人：@author xxxxx
 * 创建时间：2021/4/6/10:52
 */

import com.lxz.user.service.RegisterService;
import com.lxz.user.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
    @Autowired
    RegisterService registerService;


    @GetMapping("/sendRegisterEmail" )
    public void sendRegisterEmail(@RequestParam("email") String email) {
        registerService.sendRegisterEmail(email);
    }

    @GetMapping("/haveEmail")
    public Boolean haveEmail(@RequestParam("email") String email){
        Boolean aBoolean = registerService.haveEmail(email);
        return aBoolean;
    }

    @PostMapping("/register")
    public ResultVO register(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("varifyCode") String varifyCode ,@RequestParam("pwd") String pwd){
        ResultVO register = registerService.register(email, name, varifyCode, pwd);
        return register;
    }


}
