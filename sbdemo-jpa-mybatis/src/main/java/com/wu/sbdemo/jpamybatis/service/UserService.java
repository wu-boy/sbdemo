package com.wu.sbdemo.jpamybatis.service;

import com.wu.sbdemo.jpamybatis.dao.UserMapper;
import com.wu.sbdemo.jpamybatis.dao.UserRepository;
import com.wu.sbdemo.jpamybatis.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author: wusq
 * @date: 2018/10/25
 */
@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public void testTransaction(){
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("admin");
        userRepository.save(admin);

        User test = new User();
        test.setId("1");
        test.setUsername("test");
        test.setPassword("test");
        userMapper.save(test);

        // 抛出异常则回滚事务
        //throw new RuntimeException();
    }
}
