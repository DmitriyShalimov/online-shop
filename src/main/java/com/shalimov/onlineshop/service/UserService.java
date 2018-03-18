package com.shalimov.onlineshop.service;

import com.shalimov.onlineshop.entity.User;

public interface UserService {
    User authenticate(String name, String password);

    void addUser(User user);
}
