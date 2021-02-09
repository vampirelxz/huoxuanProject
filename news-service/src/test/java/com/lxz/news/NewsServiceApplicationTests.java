package com.lxz.news;

import com.lxz.news.service.NewsInfoService;
import com.lxz.news.service.impl.NewsInfoServiceImpl;
import com.lxz.news.utils.TranChannelUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootTest
class NewsServiceApplicationTests {
    @Autowired
    NewsInfoService newsInfoService;
    @Autowired
    TranChannelUtil tranChannelUtil;
    @Test
    void contextLoads() {
        System.out.println(newsInfoService.listNewsInfo().toString());
    }

    @Test
    void ServiceTest(){
        newsInfoService.listNewByClassify(tranChannelUtil.channel("国内"));
    }
}
