package com.shalimov.onlineshop.dao.parser;

import com.shalimov.onlineshop.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserParser {
    public List<User> getUsers(ResultSet resultSet) throws SQLException {
        List<User> products = new ArrayList<>();
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));
            products.add(user);
        }
        return products;
    }
}
