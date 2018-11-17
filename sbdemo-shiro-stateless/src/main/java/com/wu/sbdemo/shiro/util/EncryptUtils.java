package com.wu.sbdemo.shiro.util;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * 加密工具类
 * @author: wusq
 * @date: 2018/11/16
 */
public class EncryptUtils {

    /**
     * 默认散列次数
     */
    public static final Integer HASH_ITERATIONS = 2;

    /**
     * MD5加密
     * @param password 密码
     * @param salt 盐
     * @return
     */
    public static String md5(String password, String salt){
        return new Md5Hash(password, salt, HASH_ITERATIONS).toHex();
    }
}
