package com.shalimov.onlineshop.web.util;

import javax.servlet.http.Cookie;

public class Util {
    public static Cookie getCookie(Cookie[] cookies) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("user-token".equals(cookie.getName())) {
                    return cookie;
                }
            }
        }
        return null;
    }
}
