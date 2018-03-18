package com.shalimov.onlineshop.security;

import java.util.*;

public class SecurityService {
    private List<Session> sessions = new ArrayList<>();

    public boolean isValid(String token) {
        for (Session session : sessions) {
            if (token.equals(session.getToken()))
                return true;
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
