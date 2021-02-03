package com.lxz.user.dao;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/2/2/16:50
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.user.dao
 * 文件描述: @Description: 
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import com.lxz.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 包名称： com.lxz.user.dao
 * 类名称：UserMapper
 * 类描述：
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/2/2/16:50
 */

@Mapper
public interface UserMapper {
    /**
     * @Title: getUser
     * @Description: 查询用户信息根据email
     * @param email
     * @return:   User
     * @throws
     * @author: liuxuanzhi 
     * @Date:  2021/2/2/16:52 
     */
    User getUserByEmail(String email);
}
