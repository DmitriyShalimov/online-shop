package com.shalimov.onlineshop.web.security;

import com.shalimov.onlineshop.security.SecurityService;
import ua.shalimov.ioc.context.ApplicationContext;
import ua.shalimov.ioc.context.ClassPathApplicationContext;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SecurityFilter implements Filter {

    private ApplicationContext applicationContext = new ClassPathApplicationContext("src/main/resources/context.xml");
    private SecurityService securityService= (SecurityService) applicationContext.getBean("securityService");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        boolean isAuth = false;
        if (httpServletRequest.getCookies() != null) {
            for (Cookie cookie : httpServletRequest.getCookies()) {
                if ("user-token".equals(cookie.getName())) {
                    if (securityService.isValid(cookie)) {
                        isAuth = true;
                    } else {
                        isAuth = false;
                        cookie = new Cookie("user-token", null);
                        cookie.setMaxAge(0);
                        httpServletResponse.addCookie(cookie);
                    }

                    break;
                    //16 min add cookie
                }
            }
        }
        if (isAuth) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            httpServletResponse.sendRedirect("/products");
        }
    }

    @Override
    public void destroy() {
    }
}