package com.shalimov.onlineshop.dao.jdbc;

import com.shalimov.onlineshop.dao.ProductDao;
import com.shalimov.onlineshop.dao.jdbc.mapper.ProductRowMapper;
import com.shalimov.onlineshop.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcProductDao implements ProductDao {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private static final ProductRowMapper PRODUCT_ROW_MAPPER = new ProductRowMapper();
    private static final String GET_ALL_PRODUCTS_SQL = "SELECT p.id, p.title, p.Price, p.description FROM product AS p";
    private static final String ADD_NEW_PRODUCT_SQL = "INSERT INTO product (title,description,price) VALUES(?,?,?);";
    private DataSource dataSource ;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Product> getAll() {
        logger.info("start method getAll");
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(GET_ALL_PRODUCTS_SQL)) {
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                products.add(PRODUCT_ROW_MAPPER.mapRow(resultSet));
            }
            return products;
        } catch (SQLException e) {
            logger.error("error while getting products from the database:", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(Product product) {
        logger.info("start method addProduct");
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_PRODUCT_SQL)) {
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
