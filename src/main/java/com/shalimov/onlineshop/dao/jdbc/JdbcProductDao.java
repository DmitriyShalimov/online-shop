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
    private static final String GET_ALL_PRODUCTS_SQL = "SELECT id, title, price, description,image FROM \"product\" ";
    private static final String ADD_NEW_PRODUCT_SQL = "INSERT INTO \"product\" (title,description,price,image) VALUES(?,?,?,?);";
    private static final String DEFAULT_IMAGE="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSGvElxELn-hVBEc3LYWfWBZQrTpYUxfsqUtoyLYI5tGxromDEy";
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Product> getAll() {
        logger.info("start getting all products from the database");
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
        logger.info("Start of the new product's upload to the database");
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_PRODUCT_SQL)) {
            preparedStatement.setString(1, product.getTitle());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            if(product.getImage()!=null){
                preparedStatement.setString(4, product.getImage());
            }
            else{
                preparedStatement.setString(4, DEFAULT_IMAGE);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQL exception while adding new product. {}  {}", product, e);
            throw new RuntimeException("SQL exception while adding new product",e);
        }
    }
}
