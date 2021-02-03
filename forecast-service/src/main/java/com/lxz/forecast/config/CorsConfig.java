package com.lxz.forecast.config;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/2/1/13:32
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.forecast.config
 * 文件描述: @Description: 跨域配置
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/

/**
 * 包名称： com.lxz.forecast.config
 * 类名称：CorsConfig
 * 类描述：跨域配置
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/2/1/13:32
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 解决异步访问跨域
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //本应用的所有方法都会去处理跨域请求
        registry.addMapping("/**")
                //允许远端访问的域名
                .allowedOrigins("http://localhost:8080")
                //允许请求的方法("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .allowedMethods("*")
                //允许请求头
                .allowedHeaders("*");
    }
}