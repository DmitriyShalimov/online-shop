package com.shalimov.onlineshop.web.servlets;

import com.shalimov.onlineshop.entity.User;
import com.shalimov.onlineshop.security.SecurityService;
import com.shalimov.onlineshop.security.Session;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

public class RegistrationServlet extends HttpServlet {

    private SecurityService securityService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter("registrationName");
        String password = request.getParameter("registrationPassword");
        User user = new User(login, password);
        securityService.add(user);
        String token = UUID.randomUUID().toString();
        Session session = new Session();
        session.setUser(user);
        session.setToken(token);
        session.setExpireTime(LocalDateTime.now().plusHours(1));
        securityService.addSession(session);
        Cookie cookie = new Cookie("user-token", token);
        cookie.setMaxAge(3600);
        response.addCookie(cookie);
        response.sendRedirect("/products");
    }

    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}