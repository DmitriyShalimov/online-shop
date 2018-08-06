package com.shalimov.onlineshop.security;

import com.shalimov.onlineshop.entity.User;
import com.shalimov.onlineshop.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;

import java.time.LocalDateTime;
import java.util.*;

public class SecurityService {
    private List<Session> sessions = new ArrayList<>();
    private UserService userService;

    public boolean isValid(String token) {
        return getSession(token) != null;
    }

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

    public Session getSession(String token) {
        LocalDateTime now = LocalDateTime.now();
        Iterator<Session> iterator = sessions.iterator();
        while (iterator.hasNext()) {
            Session session = iterator.next();
            if (token.equals(session.getToken())) {
                LocalDateTime expireTime = session.getExpireTime();
                if (now.isBefore(expireTime)) {
                    return session;
                } else {
                    iterator.remove();
                    break;
                }
            }
        }
        return null;
    }

    public void addSession(Session session) {
        sessions.add(session);
    }

    public void removeSession(String token) {
        sessions.removeIf(session -> session.getToken().equals(token));
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
