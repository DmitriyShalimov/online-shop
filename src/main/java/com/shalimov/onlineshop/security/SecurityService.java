package com.shalimov.onlineshop.security;

import com.shalimov.onlineshop.entity.User;
import com.shalimov.onlineshop.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.*;

public class SecurityService {
    private UserService userService;

    public void add(User user) {
        String salt = UUID.randomUUID().toString();
        String password = DigestUtils.sha1Hex(user.getPassword() + salt);
        user.setSalt(salt);
        user.setPassword(password);
        userService.add(user);
    }

    public User authenticate(String login, String password) {
        User user = userService.get(login);
        String expectedPassword = DigestUtils.sha1Hex(password + user.getSalt());
        if (user.getPassword().equals(expectedPassword)) {
            user.setPassword(null);
            user.setSalt(null);
            return user;
        } else
            return null;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
