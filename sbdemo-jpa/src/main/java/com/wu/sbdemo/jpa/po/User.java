package com.wu.sbdemo.jpa.po;

import javax.persistence.*;

/**
 * @author: wusq
 * @date: 2018/11/15
 */

@Entity
@Table(name="user_test")
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 32, unique = true, nullable = false)
    private String username;

    @Column(length = 32, nullable = false)
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
