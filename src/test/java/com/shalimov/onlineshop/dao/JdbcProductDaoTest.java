//package com.shalimov.onlineshop.dao;
//
//import com.shalimov.onlineshop.dao.jdbc.mapper.ProductRowMapper;
//import com.shalimov.onlineshop.entity.Product;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import org.mockito.Matchers;
//import org.mockito.Mockito;
//import org.powermock.api.mockito.PowerMockito;
//import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.powermock.modules.junit4.PowerMockRunner;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//
//
//import static org.junit.Assert.assertEquals;
//
//@RunWith(PowerMockRunner.class)
//@PrepareForTest({DriverManager.class, JdbcProductDao.class})
//public class JdbcProductDaoTest {
//    private Connection connection;
//    private ProductRowMapper mapper;
//
//    @Before
//    public void setUp() throws Exception {
//        PowerMockito.mockStatic(DriverManager.class);
//        connection = Mockito.mock(Connection.class);
//        PowerMockito.when(DriverManager.getConnection(Matchers.anyString(), Matchers.anyString(), Matchers.anyString())).thenReturn(connection);
//        mapper = Mockito.mock(ProductRowMapper.class);
//        PowerMockito.whenNew(ProductRowMapper.class).withNoArguments().thenReturn(mapper);
//    }
//
//    @Test
//    public void getProducts() throws SQLException {
//        //    before
//        Statement statement = Mockito.mock(Statement.class);
//        Mockito.when(connection.createStatement()).thenReturn(statement);
//        Mockito.when(statement.execute("SELECT p.id, p.title, p.Price, p.description FROM product AS p")).thenReturn(true);
//        ResultSet resultSet = Mockito.mock(ResultSet.class);
//        Mockito.when(statement.getResultSet()).thenReturn(resultSet);
//        List<Product> products = new ArrayList<>();
//        Product product=new Product();
//        products.add(product);
//        Mockito.when(mapper.getProducts(resultSet)).thenReturn(products);
//
//        //when
//        JdbcProductDao productDao=new JdbcProductDao();
//
//        //then
//        assertEquals(products, productDao.getProducts());
//    }
//
//    @Test
//    public void addProduct() throws SQLException {
//        PreparedStatement statement = Mockito.mock(PreparedStatement.class);
//        Mockito.when(connection.prepareStatement("INSERT INTO product (title,description,price) VALUES(?,?,?);")).thenReturn(statement);
//        Product product=new Product();
//
//        //when
//        JdbcProductDao productDao=new JdbcProductDao();
//        productDao.addProduct(product);
//
//        //then
//        verify(statement, times(1)).executeUpdate();
//    }
//}