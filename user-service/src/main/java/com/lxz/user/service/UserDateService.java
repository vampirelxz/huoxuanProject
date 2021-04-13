package com.lxz.user.service;

import java.text.ParseException;

/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/4/12/11:43
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.user.service
 * 文件描述: @Description: ${TODO}
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/
public interface UserDateService {
    int onlineUser(String token) throws ParseException;

    Long useTime(String email);

    int visitUser(String token);
}
