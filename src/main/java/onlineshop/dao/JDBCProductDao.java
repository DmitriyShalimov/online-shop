package onlineshop.dao;

import onlineshop.entity.Product;
import onlineshop.util.ProductParser;
import onlineshop.util.PropertiesParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;

public class JDBCProductDao implements ProductDao {
    private Logger logger = LoggerFactory.getLogger(JDBCProductDao.class);
    private ProductParser parser = new ProductParser();
    private String password = "qwerty";
    private String url = "jdbc:mysql://localhost:3306/online-shop?autoReconnect=true&useSSL=false";
    private String username = "root";

    public List<Product> getProducts() {
        logger.info("start method getProducts");
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            Class.forName("com.mysql.jdbc.Driver");
            if (connection != null) {
                Statement statement = connection.createStatement();
                statement.execute(PropertiesParser.getSQL("get_all_products"));
                ResultSet resultSet = statement.getResultSet();
                return parser.getProducts(resultSet);
            } else {
                logger.error("Failed to connect");
            }
        } catch (SQLException e) {
            logger.error("method getProducts:", e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addProduct(Product product) {
        logger.info("start method addProduct");
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            Class.forName("com.mysql.jdbc.Driver");
            if (connection != null) {
                PreparedStatement preparedStatement = connection.prepareStatement(PropertiesParser.getSQL("add_new_product"));
                preparedStatement.setString(1, product.getTitle());
                preparedStatement.setString(2, product.getDescription());
                preparedStatement.setDouble(3, product.getPrice());
                preparedStatement.executeUpdate();
            } else {
                logger.error("Failed to connect");
            }
        } catch (SQLException e) {
            logger.error("SQL exception while adding new product. [title={}], [description={}],[price={}]", product.getTitle(), product.getDescription(), product.getPrice(), e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
