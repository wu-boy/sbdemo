package com.wu.sbdemo.shiro.dao;

import com.wu.sbdemo.shiro.po.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: wusq
 * @date: 2018/11/16
 */
public interface ResourceRepository extends JpaRepository<Resource, String> {
}
