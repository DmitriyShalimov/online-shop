package com.shalimov.onlineshop.dao.jdbc.mapper;

import com.shalimov.onlineshop.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper {
    public User mapRow(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setSalt(resultSet.getString("salt"));
        return user;
    }
}
