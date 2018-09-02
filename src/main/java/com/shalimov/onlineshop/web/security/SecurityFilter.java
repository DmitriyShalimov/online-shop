package com.shalimov.onlineshop.web.security;

import com.shalimov.onlineshop.ServiceLocator;
import com.shalimov.onlineshop.security.SecurityService;
import com.shalimov.onlineshop.web.util.Util;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SecurityFilter implements Filter {
    private SecurityService securityService= (SecurityService) ServiceLocator.getService("securityService");

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        boolean isAuth = false;
        Cookie cookie = Util.getCookie(httpServletRequest.getCookies());
        if (securityService.isValid(cookie.getValue())) {
            isAuth = true;
        } else {
            cookie.setMaxAge(0);
            httpServletResponse.addCookie(cookie);
        }

        if (isAuth) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            httpServletResponse.sendRedirect("/lots");
        }
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }
}
