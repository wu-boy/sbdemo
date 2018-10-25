package com.wu.sbdemo.jpamybatis.dao;

import com.wu.sbdemo.jpamybatis.po.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: wusq
 * @date: 2018/10/25
 */

@Mapper
public interface UserMapper {

    int save(User user);

}
