package com.lxz.forecast.vo;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/2/3/13:58
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.forecast.vo
 * 文件描述: @Description: 
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import lombok.Data;

import java.io.Serializable;

/**
 * 包名称： com.lxz.forecast.vo
 * 类名称：ResultVO
 * 类描述：
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/2/3/13:58
 */

@Data
public class ResultVO implements Serializable {
    private boolean success;
    private Object object;
    private String messeage;
}
