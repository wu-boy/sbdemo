package com.wu.sbdemo.jpamybatis.dao;

import com.wu.sbdemo.jpamybatis.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: wusq
 * @date: 2018/11/25
 */
public interface UserRepository extends JpaRepository<User, String> {

}
