package com.lxz.lifetools.entity;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/1/18/16:08
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.lifetools.entity
 * 文件描述: @Description: 快递具体信息实体类
 *
 * All rights Reserved, Designed By 财富管理部
 * @Copyright:2016-2021
 *
 ********************************************************/

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 包名称： com.lxz.lifetools.entity
 * 类名称：ExpressInfo
 * 类描述：快递具体信息实体类
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/1/18/16:08
 */
@Data
public class ExpressInfo implements Serializable {
    private static final long serialVersionUID = -212646481367723621L;
    /**
     * 时间
     */
    private Date time;

    /**
     * 最新信息
     */
    private String content;
}
