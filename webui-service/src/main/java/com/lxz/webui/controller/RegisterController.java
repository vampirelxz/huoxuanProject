package com.lxz.webui.controller;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/4/6/11:11
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.webui.controller
 * 文件描述: @Description: 注册控制层
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import com.lxz.webui.consumer.api.feign.RegisterFegin;
import com.lxz.webui.entity.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 包名称： com.lxz.webui.controller
 * 类名称：RegisterController
 * 类描述：注册控制层
 * 创建人：@author xxxxx
 * 创建时间：2021/4/6/11:11
 */
@RestController
public class RegisterController {
    @Autowired
    RegisterFegin registerFegin;

    @RequestMapping(value = "/sendRegisterEmail",method = RequestMethod.GET)
    public void sendRegisterEmail(@RequestParam("email") String email){
        registerFegin.sendRegisterEmail(email);
    }

    @RequestMapping(value = "/haveEmail",method = RequestMethod.GET)
    public boolean haveEmail(@RequestParam("email") String email){
        Boolean aBoolean = registerFegin.haveEmail(email);
        return aBoolean;
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ResultVO register(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("varifyCode") String varifyCode , @RequestParam("pwd") String pwd){
        ResultVO register = registerFegin.register(name, email, varifyCode, pwd);
        return register;
    }
}
