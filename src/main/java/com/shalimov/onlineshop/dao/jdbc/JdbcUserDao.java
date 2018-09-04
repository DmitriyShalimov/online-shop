package com.shalimov.onlineshop.dao.jdbc;

import com.shalimov.onlineshop.dao.UserDao;
import com.shalimov.onlineshop.dao.jdbc.mapper.UserRowMapper;
import com.shalimov.onlineshop.entity.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class JdbcUserDao implements UserDao {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private static final UserRowMapper USER_ROW_MAPPER = new UserRowMapper();
    private static final String ADD_NEW_USER_SQL = "INSERT INTO \"user\" (login,password,salt) VALUES(:login, :password, :salt);";
    private static final String GET_USER_BY_LOGIN = "SELECT login, password,salt FROM \"user\"  WHERE login=:login";
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public void add(User user) {
        logger.info("start of the new user's upload to the database");
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("login", user.getLogin());
        params.addValue("password", user.getPassword());
        params.addValue("salt", user.getSalt());
        namedParameterJdbcTemplate.update(ADD_NEW_USER_SQL, params);
    }

    @Override
    public User get(String login) {
        logger.info("start receiving a user by name and password");
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("login", login);
       return namedParameterJdbcTemplate.queryForObject(GET_USER_BY_LOGIN, params,USER_ROW_MAPPER);
    }

    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
}
