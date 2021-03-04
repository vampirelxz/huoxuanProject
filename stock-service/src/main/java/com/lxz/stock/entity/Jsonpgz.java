package com.lxz.stock.entity;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/3/4/15:14
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.stock.entity
 * 文件描述: @Description: 
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import lombok.Data;

import java.io.Serializable;

/**
 * 包名称： com.lxz.stock.entity
 * 类名称：Jsonpgz
 * 类描述：
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/3/4/15:14
 */

@Data
public class Jsonpgz implements Serializable {
    private static final long serialVersionUID = 8427448387784616080L;
    String fundcode;
    String name;
    String jzrq;
    String dwjz;
    String gsz;
    float gszzl;
    String gztime;

}
