package com.wu.sbdemo.shiro.po;

import com.wu.sbdemo.shiro.constant.UserStatus;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 资源
 * @author: wusq
 * @date: 2018/11/16
 */
@Entity
@Table(name="user_test")
public class User {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    @Column(length = 32, unique = true, nullable = false)
    private String id;

    @Column(length = 32, nullable = false)
    private String username;

    @Column(length = 32, nullable = false)
    private String password;

    @Column(length = 32, nullable = false)
    private String salt;

    @Column(length = 32, nullable = false)
    private String status = UserStatus.NORMAL.value;

    @ManyToMany(fetch = FetchType.EAGER) // 立即从数据库中进行加载数据;
    @JoinTable(name = "user_role_test", joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = {@JoinColumn(name = "role_id") })
    private List<Role> roleList = new ArrayList<>();

    public String getCredentialsSalt(){
        return this.username + this.salt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
