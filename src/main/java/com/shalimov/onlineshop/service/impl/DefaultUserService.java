package com.shalimov.onlineshop.service.impl;

import com.shalimov.onlineshop.dao.UserDao;
import com.shalimov.onlineshop.entity.User;
import com.shalimov.onlineshop.service.UserService;

public class DefaultUserService implements UserService {

    private UserDao userDao;

    public void add(User user) {
        userDao.add(user);
    }

    public User get(String login) {
        User user = userDao.get(login);
        return user;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
