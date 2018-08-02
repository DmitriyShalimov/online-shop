package com.shalimov.onlineshop.security;

import java.time.LocalDateTime;
import java.util.*;

public class SecurityService {
    private List<Session> sessions = new ArrayList<>();

    public boolean isValid(String token) {
        return getSession(token) != null;
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
}
