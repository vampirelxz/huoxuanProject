package com.lxz.webui;

import com.lxz.webui.controller.AccountController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WebuiServiceApplicationTests {

    @Autowired
    AccountController accountController;
//    @Test
//    void contextLoads() {
//        String thisWeekData = accountController.getThisWeekData(10001);
//        System.out.println(thisWeekData);
//    }

}
