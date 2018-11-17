package com.wu.sbdemo.shiro.po;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色
 * @author: wusq
 * @date: 2018/11/16
 */
@Entity
@Table(name="role_test")
public class Role {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    @Column(length = 32, unique = true, nullable = false)
    private String id;

    @Column(length = 32, nullable = false)
    private String code;

    @Column(length = 32, nullable = false)
    private String name;

    @Column(nullable = false)
    private Boolean available = Boolean.TRUE;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_resource_test", joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "resource_id")})
    private List<Resource> resourceList = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public List<Resource> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<Resource> resourceList) {
        this.resourceList = resourceList;
    }
}
