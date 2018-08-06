package com.shalimov.onlineshop.dao.jdbc;

import com.shalimov.onlineshop.dao.UserDao;
import com.shalimov.onlineshop.dao.jdbc.mapper.UserRowMapper;
import com.shalimov.onlineshop.entity.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcUserDao implements UserDao {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private static final UserRowMapper USER_ROW_MAPPER = new UserRowMapper();
    private static final String ADD_NEW_USER_SQL = "INSERT INTO \"user\" (login,password,salt) VALUES(?,?,?);";
    private static final String GET_USER_BY_LOGIN = "SELECT login, password,salt FROM \"user\"  WHERE login=?";
    private DataSource dataSource;


    @Override
    public void add(User user) {
        logger.info("start of the new user's upload to the database");
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_USER_SQL)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getSalt());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQL exception while adding new user with login {}.  {}", user.getLogin(), e);
            throw new RuntimeException("SQL exception while adding new user", e);
        }
    }

    @Override
    public User get(String login) {
        logger.info("start receiving a user by name and password");
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return USER_ROW_MAPPER.mapRow(resultSet);
        } catch (SQLException e) {
            logger.error("error while getting user from the database:", e);
            throw new RuntimeException(e);
        }
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
