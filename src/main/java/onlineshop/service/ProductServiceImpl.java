package onlineshop.service;

import onlineshop.dao.JDBCProductDao;
import onlineshop.dao.ProductDao;
import onlineshop.entity.Product;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    @Override
    public List<Product> getProducts() {
        ProductDao productDao=new JDBCProductDao();
        return productDao.getProducts();
    }

    @Override
    public void addProduct(Product product) {
        ProductDao productDao=new JDBCProductDao();
        productDao.addProduct(product);
    }
}
