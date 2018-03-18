package com.shalimov.onlineshop.web.servlets;

import com.shalimov.onlineshop.entity.Product;
import com.shalimov.onlineshop.service.ProductService;
import com.shalimov.onlineshop.service.ServiceLocator;
import com.shalimov.onlineshop.web.templater.PageGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AddNewProductServlet extends HttpServlet {
    private ProductService service = ServiceLocator.getService(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        response.getWriter().println(PageGenerator.instance().getPage("AddNewProduct.html", pageVariables));
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
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
            Product product = new Product();
            product.setTitle(title);
            product.setPrice(Double.parseDouble(price));
            product.setDescription(description);
            service.addProduct(product);
        }
        response.sendRedirect("/products");
    }
}
