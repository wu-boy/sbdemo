package com.wu.sbdemo.jpa.test;

import com.wu.sbdemo.jpa.dao.UserRepository;
import com.wu.sbdemo.jpa.po.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author: wusq
 * @date: 2018/10/25
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void test() throws Exception {

        /*User user = new User();
        user.setUsername("wusq");
        user.setPassword("123456");
        userRepository.save(user);*/

        List<User> list = userRepository.findByUsernameLike("wu%");
        System.out.println(list.get(0).getPassword());
    }
}
