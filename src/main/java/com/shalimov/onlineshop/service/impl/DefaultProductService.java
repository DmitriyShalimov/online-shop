package com.shalimov.onlineshop.service.impl;

import com.shalimov.onlineshop.dao.ProductDao;
import com.shalimov.onlineshop.entity.Product;
import com.shalimov.onlineshop.service.ProductService;

import java.util.List;

public class DefaultProductService implements ProductService {

    private ProductDao productDao;

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public List<Product> getAll() {
        return productDao.getAll();
    }

    @Override
    public void add(Product product) {
        productDao.add(product);
    }
}
