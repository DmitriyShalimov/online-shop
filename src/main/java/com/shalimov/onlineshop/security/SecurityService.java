package com.shalimov.onlineshop.security;

import javax.servlet.http.Cookie;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class SecurityService {
    private List<Session> sessions = new ArrayList<>();

    public boolean isValid(Cookie cookie) {
        Session sessionToDelete = null;
        for (Session session : sessions) {
            if (cookie.getValue().equals(session.getToken())) {
                LocalDateTime expireTime = session.getExpireTime();
                Duration interval = Duration.between(LocalDateTime.now(), expireTime);
                if (!interval.isNegative()) {
                    return true;
                } else {
                    sessionToDelete = session;
                    break;
                }
            }
        }
        if (sessionToDelete != null) {
            sessions.remove(sessionToDelete);
        }
        return false;
    }

    public Session getSession(String token) {
        for (Session session : sessions) {
            if (token.equals(session.getToken()))
                return session;
        }
        return null;
    }

    public void addSession(Session session) {
        sessions.add(session);
    }

    public void removeSession(String token) {
        sessions.removeIf(session -> session.getToken().equals(token));
    }
}
