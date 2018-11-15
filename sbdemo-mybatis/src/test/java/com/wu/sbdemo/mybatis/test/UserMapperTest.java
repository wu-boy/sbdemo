package com.wu.sbdemo.mybatis.test;

import com.wu.sbdemo.mybatis.dao.UserMapper;
import com.wu.sbdemo.mybatis.po.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: wusq
 * @date: 2018/10/25
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testGetOne() throws Exception {
        User user = userMapper.getOne(1);
        System.out.println(user.getUsername());
    }
}
