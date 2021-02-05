package com.lxz.user.controller;
/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/2/2/17:05
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.user.controller
 * 文件描述: @Description: 登陆验证
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import com.lxz.user.annotation.CurrentUser;
import com.lxz.user.entity.User;
import com.lxz.user.service.LoginService;
import com.lxz.user.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 包名称： com.lxz.user.controller
 * 类名称：LoginController
 * 类描述：登陆验证
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/2/2/17:05
 */

//@CrossOrigin(origins="*",maxAge=3600)
@RestController
public class LoginController {
    @Autowired
    LoginService loginService;

    @PostMapping(value="/login" )
    public ResultVO login(@RequestParam String email,@RequestParam String pwd) {
        ResultVO resultVO = loginService.login(email,pwd);
        System.out.println(resultVO);
        return resultVO;
    }

    public String addJwt(@CurrentUser User user){
        return "添加成功, 名字：" + user.getName();
    }

}
