package com.example;

import com.example.manage.entity.UserInfo;
import com.example.manage.service.user.UserLoginService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ManageServiceApplicationTest {


    @Resource
    UserLoginService service;

    @Test
    public void contextLoads() {
        UserInfo userInfo = new UserInfo();
        userInfo.setPassword("15611321516");
        userInfo.setEmail("2650020302@qq.com");
        userInfo.setPhone("15611321516");
        userInfo.setUserName("小样世界");
        service.saveUserInfo(userInfo);
    }

}
