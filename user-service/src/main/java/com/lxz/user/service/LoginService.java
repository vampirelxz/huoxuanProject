package com.lxz.user.service;

import com.lxz.user.vo.ResultVO;

/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/2/2/16:56
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.user.service
 * 文件描述: @Description: ${TODO}
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/
public interface LoginService {
    /**
     * @Title: login
     * @Description:登陆验证
    * @param email
     * @return:   int
     * @throws
     * @author: liuxuanzhi
     * @Date:  2021/2/2/16:57
     */
    ResultVO login(String email,String pwd);
}
