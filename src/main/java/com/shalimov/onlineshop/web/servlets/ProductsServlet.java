package com.shalimov.onlineshop.web.servlets;

import com.shalimov.onlineshop.util.Context;
import com.shalimov.onlineshop.security.Session;
import com.shalimov.onlineshop.security.SecurityService;
import com.shalimov.onlineshop.web.templater.PageGenerator;
import com.shalimov.onlineshop.entity.Product;
import com.shalimov.onlineshop.service.ProductService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductsServlet extends HttpServlet {
    private SecurityService securityService = (SecurityService) Context.instance().getApplicationContext().getBean("securityService");
    private ProductService productService = (ProductService) Context.instance().getApplicationContext().getBean("productService");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Product> list = productService.getAll();
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("products", list);
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("user-token".equals(cookie.getName())) {
                    Session session = securityService.getSession(cookie.getValue());
                    if (session != null) {
                        String login = session.getUser().getLogin();
                        pageVariables.put("login", login);
                    }
                    break;
                }
            }
        }
        response.getWriter().println(PageGenerator.instance().getPage("products.html", pageVariables));
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
