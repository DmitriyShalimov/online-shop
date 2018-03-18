package com.shalimov.onlineshop.dao;

import com.shalimov.onlineshop.entity.Product;

import java.util.List;

public interface ProductDao {
    List<Product> getProducts();

    void addProduct(Product product);
}
