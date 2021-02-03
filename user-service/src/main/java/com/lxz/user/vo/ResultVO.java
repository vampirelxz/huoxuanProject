package com.lxz.user.vo;/****************************************************
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

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class ResultVO implements Serializable {
    private boolean success;

    private String message;

    private Object object;

    public  ResultVO(){
    }

    public  ResultVO(boolean success,String message){
        this.success=success;
        this.message=message;
    }
}
