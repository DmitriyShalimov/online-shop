package com.shalimov.onlineshop.web.servlets;

import com.shalimov.onlineshop.util.Context;
import com.shalimov.onlineshop.security.SecurityService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    private SecurityService securityService = (SecurityService) Context.instance().getApplicationContext().getBean("securityService");

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
