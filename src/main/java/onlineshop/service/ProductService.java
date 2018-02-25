package onlineshop.service;

import onlineshop.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();
    void addProduct(Product product);
}
