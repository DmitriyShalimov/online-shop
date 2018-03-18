package com.shalimov.onlineshop.dao;

import com.shalimov.onlineshop.dao.parser.ProductParser;
import com.shalimov.onlineshop.util.PropertiesParser;
import com.shalimov.onlineshop.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;

public class JDBCProductDao implements ProductDao {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private ProductParser parser = new ProductParser();
    private PropertiesParser propertiesParser = new PropertiesParser();
    private String password = propertiesParser.getSQL("password");
    private String url = propertiesParser.getSQL("url");
    private String username = propertiesParser.getSQL("username");
    private final String GET_ALL_PRODUCTS_SQL = "SELECT p.id, p.title, p.Price, p.description FROM product AS p";
    private final String ADD_NEW_PRODUCT_SQL = "INSERT INTO product (title,description,price) VALUES(?,?,?);";
    private Connection connection = DriverManager.getConnection(url, username, password);

    public JDBCProductDao() throws SQLException {
    }

    public List<Product> getProducts() {
        logger.info("start method getProducts");
        try (Statement statement = connection.createStatement()) {
            statement.execute(GET_ALL_PRODUCTS_SQL);
            ResultSet resultSet = statement.getResultSet();
            return parser.getProducts(resultSet);
        } catch (SQLException e) {
            logger.error("method getProducts:", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addProduct(Product product) {
        logger.info("start method addProduct");
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_PRODUCT_SQL)) {
            preparedStatement.setString(1, product.getTitle());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQL exception while adding new product. [title={}], [description={}],[price={}]", product.getTitle(), product.getDescription(), product.getPrice(), e);
            throw new RuntimeException(e);
        }
    }
}
