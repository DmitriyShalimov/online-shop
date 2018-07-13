package com.shalimov.onlineshop;

import com.shalimov.onlineshop.web.security.SecurityFilter;
import com.shalimov.onlineshop.web.servlets.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import javax.servlet.DispatcherType;
import java.util.EnumSet;


public class Main {
    public static void main(String[] args) throws Exception {
//        //db
//        Properties properties = new Properties();
//        properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("DAO.properties"));
//        MysqlDataSource mysqlDataSource = new MysqlDataSource();
//        mysqlDataSource.setURL(properties.getProperty("url"));
//        mysqlDataSource.setPassword(properties.getProperty("password"));
//        mysqlDataSource.setUser(properties.getProperty("username"));





        //web
        ProductsServlet productsServlet = new ProductsServlet();
        AddNewProductServlet addNewProductServlet = new AddNewProductServlet();
        LoginServlet loginServlet = new LoginServlet();
        LogoutServlet logoutServlet = new LogoutServlet();
        RegistrationServlet registrationServlet = new RegistrationServlet();

        SecurityFilter securityFilter = new SecurityFilter();

        //register servlets
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(productsServlet), "/products");
        context.addServlet(new ServletHolder(addNewProductServlet), "/products/add");
        context.addServlet(new ServletHolder(loginServlet), "/login");
        context.addServlet(new ServletHolder(logoutServlet), "/logout");
        context.addServlet(new ServletHolder(registrationServlet), "/registration");

        EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST);

        context.addFilter(new FilterHolder(securityFilter), "/products/add", dispatcherTypes);

        //server config
        Server server = new Server(8080);
        server.setHandler(context);
        server.start();
    }
}
