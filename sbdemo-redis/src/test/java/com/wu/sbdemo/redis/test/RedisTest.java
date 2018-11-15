package com.wu.sbdemo.redis.test;

import com.wu.sbdemo.redis.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: wusq
 * @date: 2018/11/15
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ValueOperations<String,Object> valueOperations;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testObject() throws Exception {
        User user = new User();
        user.setId("1");
        user.setUsername("admin");
        user.setPassword("admin");

        //valueOperations.set("user", user);
        //System.out.println(valueOperations.get("user"));
        User o = (User)valueOperations.get("user");
        System.out.println(o.getUsername());
    }
}
