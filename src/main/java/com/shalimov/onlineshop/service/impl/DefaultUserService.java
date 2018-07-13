package com.shalimov.onlineshop.service.impl;

import com.shalimov.onlineshop.dao.UserDao;
import com.shalimov.onlineshop.entity.User;
import com.shalimov.onlineshop.service.UserService;

public class DefaultUserService implements UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void add(User user) {
        userDao.add(user);
    }

    public User authenticate(String name, String password) {
        for (User user : userDao.getAll()) {
            if (user.getLogin().equals(name) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}
