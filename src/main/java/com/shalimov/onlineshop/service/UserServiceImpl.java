package com.shalimov.onlineshop.service;

import com.shalimov.onlineshop.dao.UserDao;
import com.shalimov.onlineshop.entity.User;

public class UserServiceImpl implements UserService {

    private UserDao userDao = ServiceLocator.getService(UserDao.class);

    public void addUser(User user) {
        userDao.addUser(user);
    }

    public User authenticate(String name, String password) {
        for (User user : userDao.getUsers()) {
            if (user.getLogin().equals(name) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}
