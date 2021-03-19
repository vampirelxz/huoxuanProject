package com.lxz.webui.utils;/****************************************************
 * 创建人：     @author xxxxx    
 * 创建时间: 2021/3/19/15:25
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.webui.utils
 * 文件描述: @Description: 垃圾分类工具类
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import org.springframework.stereotype.Service;

/**
 * 包名称： com.lxz.webui.utils
 * 类名称：GarbageUtil
 * 类描述：垃圾分类工具类
 * 创建人：@author xxxxx
 * 创建时间：2021/3/19/15:25
 */
@Service
public class GarbageUtil {
    public String isCivilized(String s){
        String date=s;
        if(date.isEmpty()){
            return "？？？";
        }
        if(s.contains("妈")==true||s.contains("爹")==true||s.contains("爸")||s.contains("儿子")||s.contains("孙子")){
            date="日你先人板板！";
            return date;
        }else if(s.contains("你好")){
            date="你好，我有什么可以帮助你的!";
        }else if(s.contains("日")==true||s.contains("逼")==true||s.contains("傻")||s.contains("草")||s.contains("艹")||s.contains("操")){
            date="请文明用语！";
        }
        return date;
    }
}
