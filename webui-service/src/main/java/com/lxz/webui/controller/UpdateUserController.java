package com.lxz.webui.controller;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/4/7/13:43
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.webui.controller
 * 文件描述: @Description: 更新用户信息控制层
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import com.lxz.webui.consumer.api.feign.GatewayFeign;
import com.lxz.webui.consumer.api.feign.UpdateUserFeign;
import com.lxz.webui.entity.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 包名称： com.lxz.webui.controller
 * 类名称：UpdateUserController
 * 类描述：更新用户信息控制层
 * 创建人：@author xxxxx
 * 创建时间：2021/4/7/13:43
 */
@RestController
public class UpdateUserController {
    @Autowired
    GatewayFeign gatewayFeign;

    @Autowired
    UpdateUserFeign updateUserFeign;

    @GetMapping("/sendUpdatePwdEmail")
    void sendUpdatePwdEmail(@RequestParam("email") String email){
        updateUserFeign.sendUpdatePwdEmail(email);
    }

    @PostMapping("/judgeVerifyCode")
    ResultVO judgeVerifyCode(@RequestParam("email") String email, @RequestParam("verifyCode") String verifyCode){
        ResultVO resultVO = updateUserFeign.judgeVerifyCode(email,verifyCode);
        return resultVO;
    }

    @PostMapping("/updatePwd")
    ResultVO updatePwd(@RequestParam("email") String email,@RequestParam("pwd") String pwd,@RequestParam("token") String token){
        ResultVO resultVO = gatewayFeign.updatePwd(email, pwd, token);
        return resultVO;
    }
}
