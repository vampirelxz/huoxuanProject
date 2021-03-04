package com.lxz.stock.utils;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/1/14/14:27
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.stock.utils
 * 文件描述: @Description: 将json处理的工具包
 *
 * All rights Reserved, Designed By 财富管理部
 * @Copyright:2016-2021
 *
 ********************************************************/

import com.alibaba.fastjson.JSON;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * 包名称： com.lxz.stock.utils
 * 类名称：JsonUtil
 * 类描述：将json处理的工具包
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/1/14/14:27
 */
@Component
public  class JsonUtil {
    /**
     * @Title: JsonToObject
     * @Description: 将带[]的json数据除去[]
 * @param responseEntity
     * @param clazz
     * @return:  T
     * @throws
     * @author: liuxuanzhi
     * @Date:  2021/1/14/14:33
     */


    public  <T> T jsonToObject(ResponseEntity<String> responseEntity, Class<T> clazz){
        String json=responseEntity.getBody();
        json=json.substring(1,json.length()-1);
        T t= JSON.parseObject(json, clazz);
        return t;
    }

    public  <T> T jsonToObject(String jsonArray, Class<T> clazz){
        String json=null;
        json=jsonArray.substring(1,jsonArray.length()-1);
        T t= JSON.parseObject(json, clazz);
        return t;
    }

}
