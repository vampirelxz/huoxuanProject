package com.lxz.user.service.impl;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/4/6/10:42
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.user.service.impl
 * 文件描述: @Description: 注册实现类
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import com.lxz.user.dao.UserMapper;
import com.lxz.user.entity.User;
import com.lxz.user.service.RegisterService;
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
 * 类名称：RegisterServiceImpl
 * 类描述：注册实现类
 * 创建人：@author xxxxx
 * 创建时间：2021/4/6/10:42
 */
@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    SendEmailUtil sendEmailUtil;

    @Resource
    UserMapper userMapper;

    Map<String,String> emails=new HashMap<>();

    @Override
    public void sendRegisterEmail(String email) {
        String verifyCode=VerifyCodeUtils.generateVerifyCode(5);
        ResultVO resultVO = sendEmailUtil.sendMail(email, "火玄智慧生活注册验证", "Dear " + email + "用户您好，欢迎使用火玄智慧生活平台，您的验证码为：" + verifyCode + "\n,祝您使用愉快。");
        emails.put(email,verifyCode.toLowerCase());
        System.out.println(resultVO.getMessage());
    }

    @Override
    public ResultVO register(String email, String name, String varifyCode, String pwd) {
        boolean contains = emails.get(email).contains(varifyCode.toLowerCase());
        ResultVO resultVO = new ResultVO();
        if(contains == false){
            resultVO.setMessage("验证码错误");
            resultVO.setSuccess(false);
            return resultVO;
        }
        if(haveEmail(email) == false){
            resultVO.setSuccess(false);
            resultVO.setMessage("该用户已经存在，请不要修改网页");
            return resultVO;
        }
        userMapper.insertUser(email,name,pwd);
        resultVO.setMessage("添加成功");
        resultVO.setSuccess(true);
        return resultVO;
    }

    @Override
    public Boolean haveEmail(String email) {
        User userByEmail = userMapper.getUserByEmail(email);
        if (userByEmail == null){
            return true;
        }else{
            return false;
        }
    }


}
