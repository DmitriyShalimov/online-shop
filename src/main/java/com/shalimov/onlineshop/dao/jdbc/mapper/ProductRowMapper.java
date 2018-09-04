package com.shalimov.onlineshop.dao.jdbc.mapper;

import com.shalimov.onlineshop.entity.Product;
import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {

    public Product  mapRow(ResultSet resultSet) throws SQLException {
        return mapRow(resultSet,0);
    }

    public Product mapRow(ResultSet resultSet,int i) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt("id"));
        product.setTitle(resultSet.getString("title"));
        product.setPrice(resultSet.getDouble("price"));
        product.setDescription(resultSet.getString("description"));
        product.setImage(resultSet.getString("image"));
        return product;
    }
}
