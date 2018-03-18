package com.shalimov.onlineshop.web.servlets;

import com.shalimov.onlineshop.security.SecurityService;
import com.shalimov.onlineshop.service.ServiceLocator;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    private SecurityService securityService = ServiceLocator.getService(SecurityService.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("user-token")) {
                securityService.removeSession(cookie.getValue());
                break;
            }
        }
        Cookie cookie = new Cookie("user-token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        response.sendRedirect("/products");
    }
}
