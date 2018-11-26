package com.enjoylearning.mybatis.entity;

import java.util.List;

public class TRole {
    private int id;
    private String name;
    private int userId;
    private List<TUser> users;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<TUser> getUsers() {
        return users;
    }

    public void setUsers(List<TUser> users) {
        this.users = users;
    }
}
