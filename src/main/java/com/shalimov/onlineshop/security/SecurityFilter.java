package com.shalimov.onlineshop.security;


import com.shalimov.onlineshop.service.ServiceLocator;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

public class SecurityFilter implements Filter {
    private SecurityService securityService = ServiceLocator.getService(SecurityService.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        if (!httpServletRequest.getRequestURI().equals("/products/add")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            boolean isAuth = false;
            if (httpServletRequest.getCookies() != null) {
                for (Cookie cookie : httpServletRequest.getCookies()) {
                    if (cookie.getName().equals("user-token")) {
                        if (securityService.isValid(cookie.getValue())) {
                            LocalDateTime expireTime = securityService.getSession(cookie.getValue()).getExpireTime();
                            Duration interval = Duration.between(LocalDateTime.now(), expireTime);
                            if (!interval.isNegative()) {
                                isAuth = true;
                            }else{
                                securityService.removeSession(cookie.getValue());
                                cookie.setMaxAge(0);
                                httpServletResponse.addCookie(cookie);
                            }
                        }
                        break;
                    }
                }
            }
            if (isAuth) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                httpServletResponse.sendRedirect("/products");
            }
        }

    }

    @Override
    public void destroy() {
    }
}
