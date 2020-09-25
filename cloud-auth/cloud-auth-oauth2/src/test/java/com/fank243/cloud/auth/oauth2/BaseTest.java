package com.fank243.cloud.auth.oauth2;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Oauth2Application.class)
public class BaseTest {

    @Before
    public void before() {
        System.out.println("*********************单元测试开始******************************");
    }

    @After
    public void after() {
        System.out.println("*********************单元测试结束******************************");
    }
}