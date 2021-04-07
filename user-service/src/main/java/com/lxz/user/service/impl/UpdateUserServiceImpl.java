package com.lxz.user.service.impl;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/4/7/10:40
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.user.service.impl
 * 文件描述: @Description: 更新用户信息实现类
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import com.lxz.user.controller.AuthController;
import com.lxz.user.dao.UserMapper;
import com.lxz.user.service.UpdateUserService;
import com.lxz.user.utils.SendEmailUtil;
import com.lxz.user.utils.VerifyCodeUtils;
import com.lxz.user.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 包名称： com.lxz.user.service.impl
 * 类名称：UpdateUserServiceImpl
 * 类描述：更新用户信息实现类
 * 创建人：@author xxxxx
 * 创建时间：2021/4/7/10:40
 */
@Service
public class UpdateUserServiceImpl implements UpdateUserService {
    @Autowired
    SendEmailUtil sendEmailUtil;
    @Autowired
    AuthController authController;
    @Resource
    UserMapper userMapper;

    Map<String,String> emails=new HashMap<>();

    @Override
    public void sendUpdatePwdEmail(String email) {
        String verifyCode= VerifyCodeUtils.generateVerifyCode(5);
        ResultVO resultVO = sendEmailUtil.sendMail(email, "火玄智慧生活修改密码验证", "Dear " + email + "用户您好，欢迎使用火玄智慧生活平台，您的验证码为：" + verifyCode + "\n,祝您使用愉快。");
        emails.put(email,verifyCode.toLowerCase());
        System.out.println(resultVO.getMessage());
    }

    @Override
    public ResultVO judgeVerifyCode(String email, String verifyCode) {
        boolean contains = emails.get(email).contains(verifyCode.toLowerCase());
        System.out.println(contains);
        ResultVO resultVO = new ResultVO();
        if(contains == false){
            resultVO.setMessage("验证码错误");
            resultVO.setSuccess(false);
            return resultVO;
        }else{
            resultVO=authController.updatePwdAuth(email);
            resultVO.setSuccess(true);
            resultVO.setMessage("验证成功");
            return resultVO;
        }
    }

    @Override
    public void updatepwd(String email, String pwd) {
        if(!pwd.isEmpty() && pwd.length()>=5 && !email.isEmpty()) {
            userMapper.updatePwd(email, pwd);
        }
    }
}
