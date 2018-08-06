package com.shalimov.onlineshop.web.servlets;

import com.shalimov.onlineshop.entity.User;
import com.shalimov.onlineshop.security.Session;
import com.shalimov.onlineshop.security.SecurityService;
import com.shalimov.onlineshop.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

public class LoginServlet extends HttpServlet {
    private UserService userService;
    private SecurityService securityService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        User user = securityService.authenticate(name, password);
        if (user != null) {
            String token = UUID.randomUUID().toString();
            Session session = new Session();
            session.setUser(user);
            session.setToken(token);
            session.setExpireTime(LocalDateTime.now().plusHours(1));
            securityService.addSession(session);
            Cookie cookie = new Cookie("user-token", token);
            cookie.setMaxAge(3600);
            response.addCookie(cookie);
        }
        response.sendRedirect("/products");
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}
