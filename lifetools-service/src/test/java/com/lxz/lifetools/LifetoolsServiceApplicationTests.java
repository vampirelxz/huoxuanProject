package com.lxz.lifetools;

import com.lxz.lifetools.service.ExpressService;
import com.lxz.lifetools.service.GarbageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class LifetoolsServiceApplicationTests {

    @Autowired
    ExpressService expressService;
    @Autowired
    GarbageService garbageService;

    @Test
    void contextLoads() {
        System.out.println(expressService.listExpress("ESL83584691743223",null).toString());
    }

    @Test
    void testGarbage(){
        System.out.println(garbageService.garbageSorting("sada"));
    }

}
