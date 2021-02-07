package com.lxz.gateway;

import com.lxz.gateway.config.AuthFilter;
import com.lxz.gateway.config.TokenFilter;
import com.lxz.gateway.config.UriKeyResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

/**
 * @Title: GatewayServerApplication
 * @Description: 启动类
 *
 * @param null
 * @return:
 * @throws
 * @author: liuxuanzhi
 * @Date:  2021/2/7/11:26
 */


@ServletComponentScan
@SpringBootApplication
@EnableEurekaClient
public class GatewayServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServerApplication.class, args);
    }

    /**
     * @ClassName SpringcloudGatewayMasterServiceApplication
     * @Desc TODO   配置限流 Bean
     * @Date 2019/6/29 17:12
     * @Version 1.0
     */
    @Bean(name = "uriKeyResolver")
    public UriKeyResolver uriKeyResolver() {
        return new UriKeyResolver();
    }

    /**
     * @ClassName SpringcloudGatewayMasterServiceApplication
     * @Desc TODO   配置认证过滤器 Bean
     * @Date 2019/6/29 17:58
     * @Version 1.0
     */
    @Bean(name = "tokenFilter")
    public TokenFilter tokenFilter() {
        return new TokenFilter();
    }

    /**
     * @ClassName SpringcloudGatewayMasterServiceApplication
     * @Desc TODO   配置jwt认证过滤器 Bean
     * @Date 2019/6/29 17:58
     * @Version 1.0
     */
    @Bean(name = "authFilter")
    public AuthFilter authFilter() {
        return new AuthFilter();
    }


}
