package com.wu.sbdemo.jpa.dao;

import com.wu.sbdemo.jpa.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author: wusq
 * @date: 2018/10/25
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByUsernameLike(String username);
}
