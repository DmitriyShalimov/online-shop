package com.shalimov.onlineshop.web.servlets;

import com.shalimov.onlineshop.security.Session;
import com.shalimov.onlineshop.security.SecurityService;
import com.shalimov.onlineshop.web.templater.PageGenerator;
import com.shalimov.onlineshop.entity.Product;
import com.shalimov.onlineshop.service.ProductService;
import com.shalimov.onlineshop.web.util.Util;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductsServlet extends HttpServlet {
    private SecurityService securityService;
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        WebContext context = new WebContext(request, response, request.getServletContext(), request.getLocale());
        List<Product> list = productService.getAll();
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("products", list);
        Cookie cookie = Util.getCookie(request.getCookies());
        if (cookie != null) {
            Session session = securityService.getSession(cookie.getValue());
            if (session != null) {
                String login = session.getUser().getLogin();
                pageVariables.put("login", login);
            }
        }
        context.setVariables(pageVariables);
        response.getWriter().println(PageGenerator.instance().getPage(context, "products"));
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

}
