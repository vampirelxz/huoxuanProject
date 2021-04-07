package com.lxz.user.controller;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/4/7/13:14
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.user.controller
 * 文件描述: @Description: 更新用户信息控制层
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import com.lxz.user.service.UpdateUserService;
import com.lxz.user.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 包名称： com.lxz.user.controller
 * 类名称：UpdateUserController
 * 类描述：更新用户信息控制层
 * 创建人：@author xxxxx
 * 创建时间：2021/4/7/13:14
 */
@RestController
public class UpdateUserController {
    @Autowired
    UpdateUserService updateUserService;

    @GetMapping("/sendUpdatePwdEmail")
    void sendUpdatePwdEmail(@RequestParam("email") String email){
        updateUserService.sendUpdatePwdEmail(email);
    }

    @PostMapping("/judgeVerifyCode")
    ResultVO judgeVerifyCode(@RequestParam("email") String email,@RequestParam("verifyCode") String verifyCode){
        ResultVO resultVO = updateUserService.judgeVerifyCode(email, verifyCode);
        return resultVO;
    }

    @PostMapping("/updatePwd")
    ResultVO updatePwd(@RequestParam("email") String email,@RequestParam("pwd") String pwd){
        ResultVO resultVO = new ResultVO();
        resultVO.setSuccess(true);
        resultVO.setMessage("修改成功");
        updateUserService.updatepwd(email,pwd);
        return resultVO;
    }
}
