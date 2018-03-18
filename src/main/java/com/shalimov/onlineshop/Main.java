package com.shalimov.onlineshop;

import com.shalimov.onlineshop.dao.JDBCProductDao;
import com.shalimov.onlineshop.dao.JDBCUserDao;
import com.shalimov.onlineshop.dao.ProductDao;
import com.shalimov.onlineshop.dao.UserDao;
import com.shalimov.onlineshop.security.SecurityService;
import com.shalimov.onlineshop.service.*;
import com.shalimov.onlineshop.security.SecurityFilter;
import com.shalimov.onlineshop.web.servlets.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class Main {
    public static void main(String[] args) throws Exception {
        //dao
        ServiceLocator.registerService(ProductDao.class, new JDBCProductDao());
        ServiceLocator.registerService(UserDao.class, new JDBCUserDao());

        //services
        ServiceLocator.registerService(ProductService.class, new ProductServiceImpl());
        ServiceLocator.registerService(UserService.class, new UserServiceImpl());
        ServiceLocator.registerService(SecurityService.class, new SecurityService());

        //web
        ProductsServlet productsServlet = new ProductsServlet();
        AddNewProductServlet addNewProductServlet = new AddNewProductServlet();
        LoginServlet loginServlet = new LoginServlet();
        LogoutServlet logoutServlet = new LogoutServlet();
        RegistrationServlet registrationServlet=new RegistrationServlet();

        SecurityFilter securityFilter = new SecurityFilter();

        //register servlets
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(productsServlet), "/products");
        context.addServlet(new ServletHolder(addNewProductServlet), "/products/add");
        context.addServlet(new ServletHolder(loginServlet), "/login");
        context.addServlet(new ServletHolder(logoutServlet), "/logout");
        context.addServlet(new ServletHolder(registrationServlet), "/registration");

        EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST);

        context.addFilter(new FilterHolder(securityFilter), "/*", dispatcherTypes);


        //server config
        Server server = new Server(8080);
        server.setHandler(context);
        server.start();
    }
}
