package com.shalimov.onlineshop.web.servlets;

import com.shalimov.onlineshop.entity.Product;
import com.shalimov.onlineshop.service.ProductService;
import com.shalimov.onlineshop.web.templater.PageGenerator;
import ua.shalimov.ioc.context.ApplicationContext;
import ua.shalimov.ioc.context.ClassPathApplicationContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddNewProductServlet extends HttpServlet {
    private ApplicationContext applicationContext = new ClassPathApplicationContext("src/main/resources/context.xml");
    private ProductService productService = (ProductService) applicationContext.getBean("productService");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().println(PageGenerator.instance().getPage("AddNewProduct.html"));
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String title = request.getParameter("title");
        String price = request.getParameter("price");
        String description = request.getParameter("description");
        response.setContentType("text/html;charset=utf-8");
        if (title == null || price == null || description == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
            Product product = new Product();
            product.setTitle(title);
            product.setPrice(Double.parseDouble(price));
            product.setDescription(description);
            productService.add(product);
        }
        response.sendRedirect("/products");
    }
}
