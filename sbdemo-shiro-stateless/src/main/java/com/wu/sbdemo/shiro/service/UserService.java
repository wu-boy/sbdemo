package com.wu.sbdemo.shiro.service;

import com.wu.sbdemo.shiro.po.User;

/**
 * @author: wusq
 * @date: 2018/11/16
 */
public interface UserService {

    User findByUsername(String username);
}
