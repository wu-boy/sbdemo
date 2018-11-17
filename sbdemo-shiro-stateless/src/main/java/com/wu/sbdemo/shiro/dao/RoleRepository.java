package com.wu.sbdemo.shiro.dao;

import com.wu.sbdemo.shiro.po.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: wusq
 * @date: 2018/11/16
 */
public interface RoleRepository extends JpaRepository<Role, String> {
}
