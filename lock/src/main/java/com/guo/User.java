package com.guo;

import java.util.List;

/**
 * @Description:
 * @author: guofengbo
 * @date: 2020-06-10 11:18
 **/
public class User {

    private String name;

    private List<User> userList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}

