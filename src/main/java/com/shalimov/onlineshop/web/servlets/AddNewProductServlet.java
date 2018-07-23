package com.shalimov.onlineshop.web.servlets;

import com.shalimov.onlineshop.util.Context;
import com.shalimov.onlineshop.entity.Product;
import com.shalimov.onlineshop.service.ProductService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddNewProductServlet extends HttpServlet {
    private ProductService productService = (ProductService) Context.instance().getApplicationContext().getBean("productService");


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String title = request.getParameter("title");
        String price = request.getParameter("price");
        String description = request.getParameter("description");
        String image = request.getParameter("image");
        if (image == null) {
            image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSGvElxELn-hVBEc3LYWfWBZQrTpYUxfsqUtoyLYI5tGxromDEy";
        }
        response.setContentType("text/html;charset=utf-8");
        if (title == null || price == null || description == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
            Product product = new Product();
            product.setImage(image);
            product.setTitle(title);
            product.setPrice(Double.parseDouble(price));
            product.setDescription(description);
            productService.add(product);
        }
        response.sendRedirect("/products");
    }
}
