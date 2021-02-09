package com.lxz.lifetools.entity;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/1/21/11:00
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.lifetools.entity
 * 文件描述: @Description: 垃圾分类实体类
 *
 * All rights Reserved, Designed By 财富管理部
 * @Copyright:2016-2021
 *
 ********************************************************/

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 包名称： com.lxz.lifetools.entity
 * 类名称：garbage
 * 类描述：垃圾分类实体类
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/1/21/11:00
 */

@Component
@Data
public class Garbage implements Serializable {
    private static final long serialVersionUID = -1874347215095341154L;
    private String name;
    private String type;
    private String explain;
    private String contain;
    private String tip;
}
