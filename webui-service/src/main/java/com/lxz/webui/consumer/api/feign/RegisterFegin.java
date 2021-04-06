package com.lxz.webui.consumer.api.feign;

import com.lxz.webui.entity.ResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/4/6/11:09
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.webui.consumer.api.feign
 * 文件描述: @Description: ${TODO}
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/
@Component
@FeignClient("user")
public interface RegisterFegin {

    /**
     * 调用邮箱发送验证码
     * @param email 邮箱
     */
    @RequestMapping(value = "/sendRegisterEmail",method = RequestMethod.GET)
    void sendRegisterEmail(@RequestParam("email") String email);

    /**
     * 判断此邮箱是否被注册
     * @param email 邮箱
     * @return Boolean
     */
    @RequestMapping(value = "/haveEmail",method = RequestMethod.GET)
    Boolean haveEmail(@RequestParam("email") String email);

    /**
     * 注册用户
     * @param name 名字
     * @param email 邮箱
     * @param varifyCode 验证码
     * @param pwd 密码
     * @return ResultVO
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    ResultVO register(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("varifyCode") String varifyCode ,@RequestParam("pwd") String pwd);
}
