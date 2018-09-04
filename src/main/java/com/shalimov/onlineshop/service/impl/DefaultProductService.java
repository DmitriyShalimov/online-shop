package com.shalimov.onlineshop.service.impl;

import com.shalimov.onlineshop.dao.ProductDao;
import com.shalimov.onlineshop.entity.Product;
import com.shalimov.onlineshop.service.ProductService;

import java.util.List;
public class DefaultProductService implements ProductService {
    private static final String DEFAULT_IMAGE = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSGvElxELn-hVBEc3LYWfWBZQrTpYUxfsqUtoyLYI5tGxromDEy";
    private ProductDao productDao;

    @Override
    public List<Product> getAll() {
        return productDao.getAll();
    }

    @Override
    public void add(Product product) {
        if (product.getImage() == null) {
            product.setImage(DEFAULT_IMAGE);
        }
        productDao.add(product);
    }

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }
}
