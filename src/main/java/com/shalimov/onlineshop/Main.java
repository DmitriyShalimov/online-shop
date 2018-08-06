package com.shalimov.onlineshop;

import com.shalimov.onlineshop.security.SecurityService;
import com.shalimov.onlineshop.service.ProductService;
import com.shalimov.onlineshop.service.UserService;
import com.shalimov.onlineshop.web.security.SecurityFilter;
import com.shalimov.onlineshop.web.servlets.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ua.shalimov.ioc.context.ApplicationContext;
import ua.shalimov.ioc.context.ClassPathApplicationContext;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class Main {

    public static void main(String[] args) throws Exception {

        //register servlets
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        ApplicationContext applicationContext = new ClassPathApplicationContext("context.xml");
        SecurityService securityService=(SecurityService) applicationContext.getBean("securityService");
        ProductService productService=(ProductService) applicationContext.getBean("productService");
        UserService userService=(UserService) applicationContext.getBean("userService");
        ProductsServlet productsServlet = new ProductsServlet();

        productsServlet.setSecurityService(securityService);
        productsServlet.setProductService(productService);
        context.addServlet(new ServletHolder(productsServlet), "/products");
        AddNewProductServlet addNewProductServlet = new AddNewProductServlet();
        addNewProductServlet.setProductService(productService);
        context.addServlet(new ServletHolder(addNewProductServlet), "/products/add");
        LoginServlet loginServlet = new LoginServlet();
        loginServlet.setSecurityService(securityService);
        loginServlet.setUserService(userService);
        context.addServlet(new ServletHolder(loginServlet), "/login");
        LogoutServlet logoutServlet = new LogoutServlet();
        logoutServlet.setSecurityService(securityService);
        context.addServlet(new ServletHolder(logoutServlet), "/logout");
        RegistrationServlet registrationServlet = new RegistrationServlet();
        registrationServlet.setSecurityService(securityService);
        context.addServlet(new ServletHolder(registrationServlet), "/registration");

        EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST);
        SecurityFilter securityFilter = new SecurityFilter();
        securityFilter.setSecurityService(securityService);
        context.addFilter(new FilterHolder(securityFilter), "/products/add", dispatcherTypes);

        //server config
        Server server = new Server(8080);
        server.setHandler(context);
        server.start();
    }
}
