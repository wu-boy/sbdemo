package com.wu.sbdemo.shiro.po;

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
@Table(name="resource_test")
public class Resource {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    @Column(length = 32, unique = true, nullable = false)
    private String id;

    @Column(length = 32, unique = true, nullable = false)
    private String code;

    @Column(length = 32, nullable = false)
    private String name;

    @Column(length = 32, nullable = false)
    private String parentId = "root";

    @Column(nullable = false)
    private Boolean available = Boolean.TRUE;

    @Transient
    private List<Resource> children = new ArrayList<>();

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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public List<Resource> getChildren() {
        return children;
    }

    public void setChildren(List<Resource> children) {
        this.children = children;
    }
}
