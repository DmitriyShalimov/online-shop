package com.shalimov.onlineshop.dao;

import com.shalimov.onlineshop.entity.User;

public interface UserDao {


    void add(String login,String password);
    User get(String login,String password);
}
