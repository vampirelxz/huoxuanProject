package com.lxz.stock.utils;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/1/14/14:21
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.stock.utils
 * 文件描述: @Description: 常用的http修改工具类
 *
 * All rights Reserved, Designed By 财富管理部
 * @Copyright:2016-2021
 *
 ********************************************************/

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

/**
 * 包名称： com.lxz.stock.utils
 * 类名称：HttpUtil
 * 类描述：常用的http修改工具类
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/1/14/14:21
 */

@Component
public class HttpUtil {

    public HttpEntity<String> InsertBaiduApiAppCode(String appCode){
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("X-Bce-Signature", "AppCode/"+appCode);
        HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);
        return requestEntity;
    }
}
