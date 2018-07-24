package com.shalimov.onlineshop.security;

import com.shalimov.onlineshop.util.Context;
import com.shalimov.onlineshop.web.security.SecurityFilter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ua.shalimov.ioc.context.ApplicationContext;

import javax.servlet.FilterChain;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Context.class, SecurityFilterTest.class})
public class SecurityFilterTest {
    private FilterChain filterChain;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private SecurityService securityService;

    @Before
    public void setUp() {
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        filterChain = Mockito.mock(FilterChain.class);
        securityService = Mockito.mock(SecurityService.class);
    }

    @Test
    public void doFilterWithoutCookies() throws Exception {
        //before
        Mockito.when(request.getCookies()).thenReturn(null);

        //when
        SecurityFilter securityFilter = new SecurityFilter();
        securityFilter.doFilter(request, response, filterChain);

        //then
        verify(response, times(1)).sendRedirect("/products");
    }

    @Test
    public void doFilterWithoutCookieWithoutNameUserToken() throws Exception {
        //before
        Cookie[] cookies = new Cookie[1];
        Cookie cookie = new Cookie("name", "value");
        cookies[0] = cookie;
        Mockito.when(request.getCookies()).thenReturn(cookies);

        //when
        SecurityFilter securityFilter = new SecurityFilter();
        securityFilter.doFilter(request, response, filterChain);

        //then
        verify(response, times(1)).sendRedirect("/products");
    }

    @Test
    public void doFilterWithCookieWithNotValidUserToken() throws Exception {
        //before
        Mockito.when(request.getRequestURI()).thenReturn("/products/add");
        Cookie[] cookies = new Cookie[1];
        Cookie cookie = new Cookie("user-token", "value");
        cookies[0] = cookie;
        Mockito.when(request.getCookies()).thenReturn(cookies);

        PowerMockito.mockStatic(Context.class);
        Context context = Mockito.mock(Context.class);
        PowerMockito.when(Context.instance()).thenReturn(context);
        ApplicationContext applicationContext = Mockito.mock(ApplicationContext.class);
        Mockito.when(context.getApplicationContext()).thenReturn(applicationContext);
        Mockito.when(applicationContext.getBean("securityService")).thenReturn(securityService);
        Mockito.when(securityService.isValid(cookie)).thenReturn(false);

        //when
        SecurityFilter securityFilter = new SecurityFilter();
        securityFilter.doFilter(request, response, filterChain);

        //then
        verify(response, times(1)).sendRedirect("/products");
    }

    @Test
    public void doFilterWithoutCookieWithValidUserToken() throws Exception {
        //before
        Mockito.when(request.getRequestURI()).thenReturn("/products/add");
        Cookie[] cookies = new Cookie[1];
        Cookie cookie = new Cookie("user-token", "value");
        cookies[0] = cookie;
        Mockito.when(request.getCookies()).thenReturn(cookies);
        PowerMockito.mockStatic(Context.class);
        Context context = Mockito.mock(Context.class);
        PowerMockito.when(Context.instance()).thenReturn(context);
        ApplicationContext applicationContext = Mockito.mock(ApplicationContext.class);
        Mockito.when(context.getApplicationContext()).thenReturn(applicationContext);
        Mockito.when(applicationContext.getBean("securityService")).thenReturn(securityService);
        Mockito.when(securityService.isValid(cookie)).thenReturn(true);

        //when
        SecurityFilter securityFilter = new SecurityFilter();
        securityFilter.doFilter(request, response, filterChain);

        //then
        verify(filterChain, times(1)).doFilter(request, response);
    }
}