package com.lxz.user.service.impl;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/2/2/16:57
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.user.service.impl
 * 文件描述: @Description: 登陆实现类
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import com.lxz.user.dao.UserMapper;
import com.lxz.user.entity.User;
import com.lxz.user.service.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 包名称： com.lxz.user.service.impl
 * 类名称：LoginServiceImpl
 * 类描述：登陆实现类
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/2/2/16:57
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    UserMapper userMapper;

    @Override
    public User login(String email) {
        User user=userMapper.getUserByEmail(email);
        if(user == null){
            return null;
        }
        return user;
    }
}
