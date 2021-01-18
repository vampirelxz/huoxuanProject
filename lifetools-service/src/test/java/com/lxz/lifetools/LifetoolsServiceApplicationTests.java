package com.lxz.lifetools;

import com.lxz.lifetools.service.ExpressService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class LifetoolsServiceApplicationTests {

    @Autowired
    ExpressService expressService;

    @Test
    void contextLoads() {
        System.out.println(expressService.listExpress("ESL83584691743223","京东快递").toString());
    }

}
