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
        //register servlets
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new ProductsServlet()), "/products");
        context.addServlet(new ServletHolder(new AddNewProductServlet()), "/products/add");
        context.addServlet(new ServletHolder(new LoginServlet()), "/login");
        context.addServlet(new ServletHolder(new LogoutServlet()), "/logout");
        context.addServlet(new ServletHolder(new RegistrationServlet()), "/registration");

        EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST);

        context.addFilter(new FilterHolder(new SecurityFilter()), "/products/add", dispatcherTypes);

        //server config
        Server server = new Server(8080);
        server.setHandler(context);
        server.start();
    }
}
