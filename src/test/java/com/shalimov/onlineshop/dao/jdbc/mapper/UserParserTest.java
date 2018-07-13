//package com.shalimov.onlineshop.dao.parser;
//
//import com.shalimov.onlineshop.entity.User;
//import org.junit.Test;
//import org.mockito.Mockito;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import static org.junit.Assert.*;
//
//public class UserParserTest {
//    @Test
////    before
//    public void testGetUsers() throws SQLException {
//        ResultSet resultSet = Mockito.mock(ResultSet.class);
//        Mockito.when(resultSet.getInt("id")).thenReturn(1);
//        Mockito.when(resultSet.getString("login")).thenReturn("login");
//        Mockito.when(resultSet.getString("password")).thenReturn("password");
//        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
//        User expectedUser = new User();
//        expectedUser.setId(1);
//        expectedUser.setPassword("password");
//        expectedUser.setLogin("login");
//
//        //when
//        UserRowMapper userParser = new UserRowMapper();
//
//        //then
//        User actualResult = userParser.getUsers(resultSet).get(0);
//        assertEquals(expectedUser.getId(), actualResult.getId());
//        assertEquals(expectedUser.getPassword(), actualResult.getPassword());
//        assertEquals(expectedUser.getLogin(), actualResult.getLogin());
//    }
//}