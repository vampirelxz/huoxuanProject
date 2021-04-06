package com.lxz.user.service;

import com.lxz.user.vo.ResultVO;

/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/4/6/10:30
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.user.service
 * 文件描述: @Description: ${TODO}
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/
public interface RegisterService {
    void sendRegisterEmail(String email);

    ResultVO register(String email, String name, String varifyCode, String pwd);

    Boolean haveEmail(String email);

}
