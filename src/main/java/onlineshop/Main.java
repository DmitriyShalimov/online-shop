package onlineshop;

import onlineshop.servlets.AddNewProductServlet;
import onlineshop.servlets.ProductsServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {
    public static void main(String[] args) throws Exception {
        ProductsServlet productsServlet =new ProductsServlet();
        AddNewProductServlet addNewProductServlet=new AddNewProductServlet();
        ServletContextHandler context=new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(productsServlet),"/online-shop");
        context.addServlet(new ServletHolder(addNewProductServlet),"/online-shop/addNewProduct");
        Server server=new Server(8080);
        server.setHandler(context);
        server.start();
    }
}
