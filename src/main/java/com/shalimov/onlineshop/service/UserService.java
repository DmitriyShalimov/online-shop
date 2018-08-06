package com.shalimov.onlineshop.service;

import com.shalimov.onlineshop.entity.User;

public interface UserService {
    User get(String login);

    void add(User user);
}
