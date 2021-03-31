package com.lxz.webui.utils;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/3/31/15:51
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.webui.utils
 * 文件描述: @Description: 获取IP
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 包名称： com.lxz.webui.utils
 * 类名称：IpUtil
 * 类描述：获取IP
 * 创建人：@author xxxxx
 * 创建时间：2021/3/31/15:51
 */

@Component
public class IpUtil {

    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.contains(",")) {
            return ip.split(",")[0];
        } else {
            return ip;
        }
    }

    public String getIp(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        String ipAddress = getIpAddress(request);
        System.out.println(ipAddress);
        return ipAddress;
    }
}
