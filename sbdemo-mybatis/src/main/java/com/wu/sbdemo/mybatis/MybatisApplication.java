package com.wu.sbdemo.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: wusq
 * @date: 2018/10/25
 */
@SpringBootApplication
@MapperScan("com.wu.sbdemo.mybatis")
public class MybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }
}
