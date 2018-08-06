package com.shalimov.onlineshop.dao;

import com.shalimov.onlineshop.entity.User;

public interface UserDao {

    void add(User user);

    User get(String login);
}
