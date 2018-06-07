package com.shalimov.onlineshop.dao;


import com.shalimov.onlineshop.entity.User;

import java.util.List;

public interface UserDao {
    List<User> getUsers();

    void addUser(User user);
}
