//package com.shalimov.onlineshop.security;
//
//
//import com.shalimov.onlineshop.service.impl.ServiceLocator;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.powermock.api.mockito.PowerMockito;
//import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.powermock.modules.junit4.PowerMockRunner;
//
//import javax.servlet.FilterChain;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import java.time.LocalDateTime;
//
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//
//@RunWith(PowerMockRunner.class)
//@PrepareForTest({ServiceLocator.class, SecurityFilterTest.class})
//public class SecurityFilterTest {
//    private FilterChain filterChain;
//    private HttpServletRequest request;
//    private HttpServletResponse response;
//
//    @Before
//    public void setUp() throws Exception {
//        request = Mockito.mock(HttpServletRequest.class);
//        response = Mockito.mock(HttpServletResponse.class);
//        filterChain = Mockito.mock(FilterChain.class);
//    }
//
//
//    @Test
//    public void doFilterWithWrongUrl() throws Exception {
//        //before
//        Mockito.when(request.getRequestURI()).thenReturn("wrong url");
//
//        //when
//        SecurityFilter securityFilter = new SecurityFilter();
//        securityFilter.doFilter(request, response, filterChain);
//
//        //then
//        verify(filterChain, times(1)).doFilter(request, response);
//    }
//
//    @Test
//    public void doFilterWithoutCookies() throws Exception {
//        //before
//        Mockito.when(request.getRequestURI()).thenReturn("/products/add");
//        Mockito.when(request.getCookies()).thenReturn(null);
//
//        //when
//        SecurityFilter securityFilter = new SecurityFilter();
//        securityFilter.doFilter(request, response, filterChain);
//
//        //then
//        verify(response, times(1)).sendRedirect("/products");
//    }
//
//    @Test
//    public void doFilterWithoutCookieWithoutNameUserToken() throws Exception {
//        //before
//        Mockito.when(request.getRequestURI()).thenReturn("/products/add");
//        Cookie[] cookies = new Cookie[1];
//        Cookie cookie = new Cookie("name", "value");
//        cookies[0] = cookie;
//
//        Mockito.when(request.getCookies()).thenReturn(cookies);
//
//        //when
//        SecurityFilter securityFilter = new SecurityFilter();
//        securityFilter.doFilter(request, response, filterChain);
//
//        //then
//        verify(response, times(1)).sendRedirect("/products");
//    }
//
//    @Test
//    public void doFilterWithoutCookieWithNotValidUserTokenValue() throws Exception {
//        //before
//        Mockito.when(request.getRequestURI()).thenReturn("/products/add");
//        Cookie[] cookies = new Cookie[1];
//        Cookie cookie = new Cookie("user-token", "value");
//        cookies[0] = cookie;
//        Mockito.when(request.getCookies()).thenReturn(cookies);
//        PowerMockito.mockStatic(ServiceLocator.class);
//        SecurityService securityService = Mockito.mock(SecurityService.class);
//        PowerMockito.when(ServiceLocator.getService(SecurityService.class)).thenReturn(securityService);
//        Mockito.when(securityService.isValid(cookie.getValue())).thenReturn(false);
//
//        //when
//        SecurityFilter securityFilter = new SecurityFilter();
//        securityFilter.doFilter(request, response, filterChain);
//
//        //then
//        verify(response, times(1)).sendRedirect("/products");
//    }
//
//
//    @Test
//    public void doFilterWithoutCookieWithValidUserTokenValue() throws Exception {
//        //before
//        Mockito.when(request.getRequestURI()).thenReturn("/products/add");
//        Cookie[] cookies = new Cookie[1];
//        Cookie cookie = new Cookie("user-token", "value");
//        cookies[0] = cookie;
//        Mockito.when(request.getCookies()).thenReturn(cookies);
//        PowerMockito.mockStatic(ServiceLocator.class);
//        SecurityService securityService = Mockito.mock(SecurityService.class);
//        PowerMockito.when(ServiceLocator.getService(SecurityService.class)).thenReturn(securityService);
//        Mockito.when(securityService.isValid(cookie.getValue())).thenReturn(true);
//        Session session = Mockito.mock(Session.class);
//        Mockito.when(securityService.getSession(cookie.getValue())).thenReturn(session);
//        Mockito.when(session.getExpireTime()).thenReturn(LocalDateTime.MAX);
//
//        //when
//        SecurityFilter securityFilter = new SecurityFilter();
//        securityFilter.doFilter(request, response, filterChain);
//
//        //then
//        verify(filterChain, times(1)).doFilter(request, response);
//    }
//
//    @Test
//    public void doFilterWithoutCookieWithValidUserTokenValueWithNegativeCookieAge() throws Exception {
//        //before
//        Mockito.when(request.getRequestURI()).thenReturn("/products/add");
//        Cookie[] cookies = new Cookie[1];
//        Cookie cookie = new Cookie("user-token", "value");
//        cookies[0] = cookie;
//        Mockito.when(request.getCookies()).thenReturn(cookies);
//        PowerMockito.mockStatic(ServiceLocator.class);
//        SecurityService securityService = Mockito.mock(SecurityService.class);
//        PowerMockito.when(ServiceLocator.getService(SecurityService.class)).thenReturn(securityService);
//        Mockito.when(securityService.isValid(cookie.getValue())).thenReturn(true);
//        Session session = Mockito.mock(Session.class);
//        Mockito.when(securityService.getSession(cookie.getValue())).thenReturn(session);
//        Mockito.when(session.getExpireTime()).thenReturn(LocalDateTime.MIN);
//
//        //when
//        SecurityFilter securityFilter = new SecurityFilter();
//        securityFilter.doFilter(request, response, filterChain);
//
//        //then
//        verify(securityService, times(1)).removeSession(cookie.getValue());
//        verify(response, times(1)).addCookie(cookie);
//        verify(response, times(1)).sendRedirect("/products");
//    }
//
//
//}