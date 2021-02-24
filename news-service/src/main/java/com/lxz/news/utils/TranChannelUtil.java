package com.lxz.news.utils;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/1/15/15:23
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.news.utils
 * 文件描述: @Description: 将前端给的汉字转为id
 *
 * All rights Reserved, Designed By 财富管理部
 * @Copyright:2016-2021
 *
 ********************************************************/

import org.springframework.stereotype.Component;

/**
 * 包名称： com.lxz.news.utils
 * 类名称：TralChannel
 * 类描述：将前端给的拼音转为id
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/1/15/15:23
 */
@Component
public class TranChannelUtil {
    public String channel(String name){
        if(name.equals("guonei")){
            return "5572a108b3cdc86cf39001cd";
        }else if(name.equals("guoji")){
            return "5572a108b3cdc86cf39001ce";
        }else if(name.equals("junshi")){
            return "5572a108b3cdc86cf39001cf";
        }else if(name.equals("tiyu")){
            return "5572a108b3cdc86cf39001d4";
        }else if(name.equals("yule")){
            return "5572a108b3cdc86cf39001d5";
        }else if(name.equals("shehui")){
            return "5572a109b3cdc86cf39001da";
        }else if(name.equals("caijing")){
            return "5572a108b3cdc86cf39001d0";
        }else if(name.equals("keji")){
            return "5572a108b3cdc86cf39001d9";
        }
        return null;
    }
}
