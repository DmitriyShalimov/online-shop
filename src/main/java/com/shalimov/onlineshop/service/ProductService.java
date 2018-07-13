package com.shalimov.onlineshop.service;

import com.shalimov.onlineshop.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
    void add(Product product);
}
