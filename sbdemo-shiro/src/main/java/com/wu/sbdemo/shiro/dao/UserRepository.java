package com.wu.sbdemo.shiro.dao;

import com.wu.sbdemo.shiro.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: wusq
 * @date: 2018/11/16
 */
public interface UserRepository extends JpaRepository<User, String> {

    User findByUsername(String username);
}
