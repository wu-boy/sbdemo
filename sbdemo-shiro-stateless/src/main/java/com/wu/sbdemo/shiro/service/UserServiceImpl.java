package com.wu.sbdemo.shiro.service;

import com.wu.sbdemo.shiro.dao.UserRepository;
import com.wu.sbdemo.shiro.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: wusq
 * @date: 2018/11/16
 */
@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
