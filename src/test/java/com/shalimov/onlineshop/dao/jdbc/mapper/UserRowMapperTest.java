package com.shalimov.onlineshop.dao.jdbc.mapper;

import com.shalimov.onlineshop.entity.User;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class UserRowMapperTest {
    @Test
//    before
    public void testMapRow() throws SQLException {
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.when(resultSet.getInt("id")).thenReturn(1);
        Mockito.when(resultSet.getString("login")).thenReturn("login");
        Mockito.when(resultSet.getString("password")).thenReturn("password");
        User expectedUser = new User();
        expectedUser.setId(1);
        expectedUser.setPassword("password");
        expectedUser.setLogin("login");

        //when
        UserRowMapper userRowMapper = new UserRowMapper();

        //then
        User actualResult = userRowMapper.mapRow(resultSet);
        assertEquals(expectedUser.getId(), actualResult.getId());
        assertEquals(expectedUser.getPassword(), actualResult.getPassword());
        assertEquals(expectedUser.getLogin(), actualResult.getLogin());
    }
}