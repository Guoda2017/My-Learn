package com.guo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @description:
 * @author: guofengbo
 * @create: 2020-02-29 20:02
 **/
public class Start {


    public static void main(String[] args) {
        List<User> userList = null;
        User user = null;
        //user.setUserList(userList);

        Person person = new Person();
        person.setUser(user);

        Optional.ofNullable(person.getUser().getUserList()).ifPresent(System.out::println);
    }

}
