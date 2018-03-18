package com.shalimov.onlineshop.dao;

import com.shalimov.onlineshop.dao.parser.UserParser;
import com.shalimov.onlineshop.entity.User;
import com.shalimov.onlineshop.util.PropertiesParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;

public class JDBCUserDao implements UserDao {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private UserParser parser = new UserParser();
    private PropertiesParser propertiesParser = new PropertiesParser();
    private String password = propertiesParser.getSQL("password");
    private String url = propertiesParser.getSQL("url");
    private String username = propertiesParser.getSQL("username");
    private final String GET_ALL_USERS_SQL = "SELECT u.id, u.login, u.password FROM user AS u";
    private final String ADD_NEW_USER_SQL = "INSERT INTO user (login,password) VALUES(?,?);";
    private Connection connection = DriverManager.getConnection(url, username, password);

    public JDBCUserDao() throws SQLException {
    }

    public List<User> getUsers() {
        logger.info("start method getUsers");
        try (Statement statement = connection.createStatement()) {
            statement.execute(GET_ALL_USERS_SQL);
            ResultSet resultSet = statement.getResultSet();
            return parser.getUsers(resultSet);
        } catch (SQLException e) {
            logger.error("method getUsers:", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addUser(User user) {
        logger.info("start method addUser");
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_USER_SQL)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQL exception while adding new user. [login={}], [password={}],[price={}]", user.getLogin(), user.getPassword(), e);
            throw new RuntimeException(e);
        }
    }
}
