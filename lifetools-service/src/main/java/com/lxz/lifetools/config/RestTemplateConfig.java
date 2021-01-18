package com.lxz.lifetools.config;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/1/12/14:03
 * 项目名称：  huoxuan
 * 文件名称: com.lxz.forecast.config
 * 文件描述: @Description: 
 *
 * All rights Reserved, Designed By 财富管理部
 * @Copyright:2016-2021
 *
 ********************************************************/



import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


/**
 * 包名称： com.lxz.forecast.config
 * 类名称：RestTemplateConfig
 * 类描述：
 * 创建人：@author liuxuanzhi
 * 创建时间：2021/1/12/14:03
 */
@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(getClientHttpRequestFactory());
    }

    /**
     * 配置HttpClient超时时间
     *
     * @return
     */
    private ClientHttpRequestFactory getClientHttpRequestFactory() {
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(HTTP_SOCKET_TIMEOUT)
                .setConnectTimeout(HTTP_CONNECT_TIMEOUT).build();
        CloseableHttpClient client = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
        return new HttpComponentsClientHttpRequestFactory(client);
    }

    /** http请求socket连接超时时间,毫秒为单位 */
    public static final int HTTP_SOCKET_TIMEOUT = 15000;

    /** http请求连接超时时间,毫秒为单位 */
    public static final int HTTP_CONNECT_TIMEOUT = 15000;

}
