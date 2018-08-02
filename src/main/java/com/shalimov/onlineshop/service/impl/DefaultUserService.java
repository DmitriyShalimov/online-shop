package com.shalimov.onlineshop.service.impl;

import com.shalimov.onlineshop.dao.UserDao;
import com.shalimov.onlineshop.entity.User;
import com.shalimov.onlineshop.service.UserService;

public class DefaultUserService implements UserService {

    private UserDao userDao;

    public void add(User user) {
        String password = "!1Qq" + user.getPassword().hashCode() + "(9Pp";
        userDao.add(user.getLogin(), password);
    }

    public User authenticate(String name, String password) {
        String expectedPassword = "!1Qq" + password.hashCode() + "(9Pp";
        User user = userDao.get(name, expectedPassword);
        user.setPassword(null);
        return user;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
