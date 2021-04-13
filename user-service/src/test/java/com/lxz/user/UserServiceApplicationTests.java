package com.lxz.user;

import com.lxz.user.utils.Md5Utils;
import com.lxz.user.utils.SendEmailUtil;
import com.lxz.user.utils.UserDateUtils;
import com.lxz.user.vo.ResultVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;

@SpringBootTest
class UserServiceApplicationTests {
    @Autowired
    SendEmailUtil sendEmailUtil;
    @Autowired
    UserDateUtils userDateUtils;


    @Test
    void contextLoads() {
    }
    @Test
    void testEmail() {
        ResultVO resultVO = sendEmailUtil.sendMail("736603914@qq.com", "火玄智慧生活助手注册验证", "测试");
        System.out.println(resultVO.toString());
    }

    @Test
    void testMd5(){
        String s = Md5Utils.MD5Encode("vampire", "UTF-8", true);
        System.out.println(s);
        String s1 = Md5Utils.MD5Encode("admin", "UTF-8", true);
        System.out.println(s1);
    }

    @Test
    void numberOnline() throws ParseException {
        userDateUtils.insertToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJYVUFOIiwiZXhwIjoxNjE4MjAwMTE5LCJ1c2VyTmFtZSI6IuWvjOixqiIsImlhdCI6MTYxODE5ODMxOSwidXNlcklkIjoxMDAwMSwiZW1haWwiOiJhZG1pbkBxcS5jb20ifQ.xaFRE8yc9B8LgmP669lGczmPoL8NKhEhUZd5FpELXo0");
        userDateUtils.insertToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJYVUFOIiwiZXhwIjoxNjE4MjAwMTU0LCJ1c2VyTmFtZSI6IuWvjOixqiIsImlhdCI6MTYxODE5ODM1NCwidXNlcklkIjoxMDAwMSwiZW1haWwiOiJhZG1pbkBxcS5jb20ifQ.RBwzRWSLGm79rlDMIUrJ6vpY9QIyjDvZqBLq_JswBfU");
        userDateUtils.insertToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJYVUFOIiwiZXhwIjoxNjE4MjAwMTc0LCJ1c2VyTmFtZSI6Iua1i-ivlTMiLCJpYXQiOjE2MTgxOTgzNzQsInVzZXJJZCI6MTAwMDUsImVtYWlsIjoiNzM2NjAzOTE0QHFxLmNvbSJ9.wyDuQyuDPcrqXHkT-GX-zzsb5UAzeJgg0zOAehzQhbU");
        Integer onlineCount = userDateUtils.getOnlineCount();
        System.out.println(onlineCount);
    }

    @Test
    void useTimeTest() throws ParseException {
        userDateUtils.insertToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJYVUFOIiwiZXhwIjoxNjE4MjEwNDEyLCJ1c2VyTmFtZSI6Iua1i-ivlTMiLCJpYXQiOjE2MTgyMDg2MTIsInVzZXJJZCI6MTAwMDUsImVtYWlsIjoiNzM2NjAzOTE0QHFxLmNvbSJ9.mFpdqjwRmzqC7YRUscq6frWAzvE73re9MjYuPh93xFY");
        userDateUtils.insertToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJYVUFOIiwiZXhwIjoxNjE4MjEwNTg1LCJ1c2VyTmFtZSI6Iua1i-ivlTMiLCJpYXQiOjE2MTgyMDg3ODUsInVzZXJJZCI6MTAwMDUsImVtYWlsIjoiNzM2NjAzOTE0QHFxLmNvbSJ9.3iZd7tHwyWHzvdAzfXQFy0CutCStDpoD-opBBLzz27o");
        System.out.println(UserDateUtils.countVisit);
        System.out.println(UserDateUtils.timeMap);
    }

}
