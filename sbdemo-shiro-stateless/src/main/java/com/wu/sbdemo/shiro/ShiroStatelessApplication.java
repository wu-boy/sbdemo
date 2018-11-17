package com.wu.sbdemo.shiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * @author: wusq
 * @date: 2018/11/16
 */
@SpringBootApplication(scanBasePackages="com.wu.sbdemo.shiro")
@EntityScan("com.wu.sbdemo.shiro")
public class ShiroStatelessApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShiroStatelessApplication.class, args);
    }
}
