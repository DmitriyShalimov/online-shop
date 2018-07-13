package com.shalimov.onlineshop.web.servlets;

import com.shalimov.onlineshop.entity.User;
import com.shalimov.onlineshop.security.Session;
import com.shalimov.onlineshop.security.SecurityService;
import com.shalimov.onlineshop.service.UserService;
import com.shalimov.onlineshop.web.templater.PageGenerator;
import ua.shalimov.ioc.context.ApplicationContext;
import ua.shalimov.ioc.context.ClassPathApplicationContext;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

public class RegistrationServlet extends HttpServlet {
    private ApplicationContext applicationContext = new ClassPathApplicationContext("src/main/resources/context.xml");
    private UserService userService = (UserService) applicationContext.getBean("userService");
    private SecurityService securityService = (SecurityService) applicationContext.getBean("securityService");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String page = PageGenerator.instance().getPage("registration.html");
        response.getWriter().write(page);
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        User user = new User(name, password);
        userService.add(user);
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
}