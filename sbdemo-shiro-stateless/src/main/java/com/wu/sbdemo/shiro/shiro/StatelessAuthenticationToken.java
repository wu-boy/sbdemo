package com.wu.sbdemo.shiro.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author: wusq
 * @date: 2018/11/17
 */
public class StatelessAuthenticationToken implements AuthenticationToken {

    private static final long serialVersionUID = 1L;

    private String token;

    public StatelessAuthenticationToken(String token){
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return this.token;
    }

    @Override
    public Object getCredentials() {
        return this.token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
