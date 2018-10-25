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
        User user1 = new User();
        user1.setUsername("user1");
        user1.setPassword("111");
        userRepository.save(user1);

        User user2 = new User();
        user2.setId(22);
        user2.setUsername("user2");
        user2.setPassword("222");
        userMapper.save(user2);

        // 抛出异常则回滚事务
        //throw new RuntimeException();
    }
}
