package com.wu.sbdemo.mybatis.dao;

import com.wu.sbdemo.mybatis.po.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: wusq
 * @date: 2018/10/25
 */

@Mapper
public interface UserMapper {

    User getOne(Integer id);

}
