package com.shalimov.onlineshop.dao.jdbc;

import com.shalimov.onlineshop.dao.ProductDao;
import com.shalimov.onlineshop.dao.jdbc.mapper.ProductRowMapper;
import com.shalimov.onlineshop.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import java.util.List;

public class JdbcProductDao implements ProductDao {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private static final ProductRowMapper PRODUCT_ROW_MAPPER = new ProductRowMapper();
    private static final String GET_ALL_PRODUCTS_SQL = "SELECT id, title, price, description,image FROM \"product\" ";
    private static final String ADD_NEW_PRODUCT_SQL = "INSERT INTO \"product\" (title,description,price,image) VALUES(:title,:description,:price,:image);";
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Product> getAll() {
        logger.info("start getting all products from the database");
        return jdbcTemplate.query(GET_ALL_PRODUCTS_SQL, PRODUCT_ROW_MAPPER);
    }

    @Override
    public void add(Product product) {
        logger.info("Start of the new product's upload to the database");
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("title", product.getTitle());
        params.addValue("description", product.getDescription());
        params.addValue("price", product.getPrice());
        params.addValue("image", product.getImage());
        namedParameterJdbcTemplate.update(ADD_NEW_PRODUCT_SQL, params);
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
}
