package com.lxz.user.service;

import com.lxz.user.entity.User;
import com.lxz.user.vo.ResultVO;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/4/8/13:36
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.user.service
 * 文件描述: @Description: ${TODO}
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/
public interface AuthService {
    /**
     * 登录授权，生成JWT
     * @param email
     * @param pwd
     * @return
     */
    ResultVO loginAuth(String email, String pwd);

    /**
     * 刷新JWT
     * @param refreshToken
     * @return
     */
    Map<String,Object> refreshToken( String refreshToken);

    String buildJWT(User user);

    String buildForgotJWT(String email);

    /**
     * 登录授权，生成JWT
     * @param email
     * @return
     */
    ResultVO updatePwdAuth(@RequestParam String email);

}
