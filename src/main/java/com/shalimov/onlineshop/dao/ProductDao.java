package com.shalimov.onlineshop.dao;

import com.shalimov.onlineshop.entity.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {
    List<Product> getAll();

    void add(Product product) ;
}
