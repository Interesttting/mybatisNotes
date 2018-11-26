package com.enjoylearning.mybatis.entity;

import java.util.List;

public class TGroup {
    private int groupId;
    private String name;
    private List<TUser> users;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TUser> getUsers() {
        return users;
    }

    public void setUsers(List<TUser> users) {
        this.users = users;
    }
}
