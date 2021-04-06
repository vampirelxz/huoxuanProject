package com.lxz.user;

import com.lxz.user.utils.SendEmailUtil;
import com.lxz.user.vo.ResultVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests {
    @Autowired
    SendEmailUtil sendEmailUtil;


    @Test
    void contextLoads() {
    }
    @Test
    void testEmail() {
        ResultVO resultVO = sendEmailUtil.sendMail("736603914@qq.com", "火玄智慧生活助手注册验证", "测试");
        System.out.println(resultVO.toString());
    }

}
