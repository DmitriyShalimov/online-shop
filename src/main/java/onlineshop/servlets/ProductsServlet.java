package onlineshop.servlets;

import onlineshop.util.ListToJson;
import onlineshop.util.PageGenerator;
import onlineshop.entity.Product;
import onlineshop.service.ProductService;
import onlineshop.service.ProductServiceImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ProductService service = new ProductServiceImpl();
        List<Product> list = service.getProducts();
        Map<String, Object> pageVariables = new HashMap<>();
        ListToJson listToJson = new ListToJson();
        String json=listToJson.convert(list);
        pageVariables.put("products", json);
        response.getWriter().println(PageGenerator.instance().getPage("products.html", pageVariables));
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        ProductService service = new ProductServiceImpl();
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
        List<Product> list = service.getProducts();
        ListToJson listToJson = new ListToJson();
        String json=listToJson.convert(list);
        pageVariables.put("products", json);
        response.getWriter().println(PageGenerator.instance().getPage("products.html", pageVariables));
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
