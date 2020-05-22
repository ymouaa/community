package com.ang.springboot_es.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * 权限
 */
public class Authority implements GrantedAuthority {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name;
    }

}
