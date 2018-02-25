package onlineshop.util;

import onlineshop.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductParser {
    private Logger logger = LoggerFactory.getLogger(ProductParser.class);
    public List<Product> getProducts(ResultSet resultSet) {
            logger.info("start method getProducts");
            List<Product> products = new ArrayList<>();
            try {
                while (resultSet.next()) {
                    Product product = new Product();
                    product.setId(resultSet.getInt("id"));
                    product.setTitle(resultSet.getString("title"));
                    product.setPrice(resultSet.getDouble("price"));
                    product.setDescription(resultSet.getString("description"));
                    products.add(product);
                }
            } catch (SQLException e) {
                logger.error("method getProducts:", e);
            }
            return products;
    }
}
