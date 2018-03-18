package com.shalimov.onlineshop.service;

import com.shalimov.onlineshop.dao.ProductDao;
import com.shalimov.onlineshop.entity.Product;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private ProductDao productDao = ServiceLocator.getService(ProductDao.class);

    @Override
    public List<Product> getProducts() {
        return productDao.getProducts();
    }

    @Override
    public void addProduct(Product product) {
        productDao.addProduct(product);
    }
}
