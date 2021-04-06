package com.lxz.webui.entity;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/2/2/16:42
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.user.entity
 * 文件描述: @Description: 用户实体类
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import lombok.Data;

import java.io.Serializable;

/**
 * 包名称： com.lxz.user.entity
 * 类名称：User
 * 类描述：用户实体类
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/2/2/16:42
 */

@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String pwd;
    private String email;
}
