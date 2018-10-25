package com.wu.sbdemo.jpamybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: wusq
 * @date: 2018/10/25
 */
@SpringBootApplication
@MapperScan("com.wu.sbdemo.jpamybatis")
public class JpaMybatisApplication {
    public static void main(String[] args) {
        SpringApplication.run(JpaMybatisApplication.class, args);
    }
}
