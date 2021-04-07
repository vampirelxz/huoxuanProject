package com.lxz.user.service;

import com.lxz.user.vo.ResultVO;

/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/4/7/10:31
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.user.service
 * 文件描述: @Description: ${TODO}
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/
public interface UpdateUserService {
    void sendUpdatePwdEmail(String email);

    ResultVO judgeVerifyCode(String email, String verifyCode);

    void updatepwd(String email,String pwd);
}
