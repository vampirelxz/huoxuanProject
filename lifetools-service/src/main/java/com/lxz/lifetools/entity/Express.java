package com.lxz.lifetools.entity;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/1/18/16:07
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.lifetools.entity
 * 文件描述: @Description: 快递快递实体类
 *
 * All rights Reserved, Designed By 财富管理部
 * @Copyright:2016-2021
 *
 ********************************************************/

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * 包名称： com.lxz.lifetools.entity
 * 类名称：Express
 * 类描述：快递快递实体类
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/1/18/16:07
 */

@Component
@Data
public class Express implements Serializable {
    private static final long serialVersionUID = -2272405814509452068L;

    /**
     * 编号
     */
    private String num;

    /**
     * 公司
     */
    private String com;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 快递具体信息
     */
    private List<ExpressInfo> info;
}
