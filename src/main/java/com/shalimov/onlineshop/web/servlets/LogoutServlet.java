package com.shalimov.onlineshop.web.servlets;

import com.shalimov.onlineshop.ServiceLocator;
import com.shalimov.onlineshop.security.SecurityService;
import com.shalimov.onlineshop.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    private SecurityService securityService= (SecurityService) ServiceLocator.getService("securityService");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        for (Cookie cookie : request.getCookies()) {
            if ("user-token".equals(cookie.getName())) {
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
