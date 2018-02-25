package onlineshop.dao;

import onlineshop.entity.Product;

import java.util.List;

public interface ProductDao {
    List<Product> getProducts();
    void addProduct(Product product);
}
