package com.lxz.lifetools.utils;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/1/21/11:18
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.lifetools.utils
 * 文件描述: @Description: 垃圾分类工具类
 *
 * All rights Reserved, Designed By 财富管理部
 * @Copyright:2016-2021
 *
 ********************************************************/

import org.springframework.stereotype.Component;

/**
 * 包名称： com.lxz.lifetools.utils
 * 类名称：GarbageUtil
 * 类描述：垃圾分类
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/1/21/11:18
 */
@Component
public class GarbageUtil {
    String type1="1";
    String type2="2";
    String type3="3";
    String type4="4";
    public String typeToName(String type){
        if(type.equals(type1)){
            return "可回收垃圾";
        }
        if(type.equals(type2)){
            return "有害垃圾";
        }
        if(type.equals(type3)){
            return "厨余垃圾（湿垃圾）";
        }
        if(type.equals(type4)){
            return "干垃圾";
        }
        return "对不起，暂未收入此垃圾信息";
    }
}
