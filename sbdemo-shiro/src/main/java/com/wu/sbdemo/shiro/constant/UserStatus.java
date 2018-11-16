package com.wu.sbdemo.shiro.constant;

/**
 * @author: wusq
 * @date: 2018/11/16
 */
public enum UserStatus {

    CANCEL("0", "注销"),
    NORMAL("1", "正常"),
    LOCKED("2", "锁定");

    public final String value;
    public final String status;

    private UserStatus(String value, String status) {
        this.value = value;
        this.status = status;;
    }

    public String value() {
        return this.value;
    }

    public String status() {
        return this.status;
    }

}
