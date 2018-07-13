//package com.shalimov.onlineshop.dao;
//
//import com.shalimov.onlineshop.dao.jdbc.mapper.UserRowMapper;
//import com.shalimov.onlineshop.entity.User;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
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
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//
//@RunWith(PowerMockRunner.class)
//@PrepareForTest({DriverManager.class, JdbcUserDao.class})
//public class JDBCUserDaoTest {
//    private Connection connection;
//    private UserRowMapper mapper;
//
//    @Before
//    public void setUp() throws Exception {
//        PowerMockito.mockStatic(DriverManager.class);
//        connection = Mockito.mock(Connection.class);
//        PowerMockito.when(DriverManager.getConnection(Matchers.anyString(), Matchers.anyString(), Matchers.anyString())).thenReturn(connection);
//        mapper = Mockito.mock(UserRowMapper.class);
//        PowerMockito.whenNew(UserRowMapper.class).withNoArguments().thenReturn(mapper);
//    }
//
//    @Test
//    public void getUsers() throws SQLException {
//        //    before
//        Statement statement = Mockito.mock(Statement.class);
//        Mockito.when(connection.createStatement()).thenReturn(statement);
//        Mockito.when(statement.execute("SELECT u.id, u.login, u.password FROM user AS u")).thenReturn(true);
//        ResultSet resultSet = Mockito.mock(ResultSet.class);
//        Mockito.when(statement.getResultSet()).thenReturn(resultSet);
//        List<User> users = new ArrayList<>();
//        User user = new User();
//        users.add(user);
//        Mockito.when(mapper.getUsers(resultSet)).thenReturn(users);
//
//        //when
//        JdbcUserDao userDao = new JdbcUserDao();
//
//        //then
//        assertEquals(users, userDao.getUsers());
//    }
//
//    @Test
//    public void addUser() throws SQLException {
//        PreparedStatement statement = Mockito.mock(PreparedStatement.class);
//        Mockito.when(connection.prepareStatement("INSERT INTO user (login,password) VALUES(?,?);")).thenReturn(statement);
//        User user = new User();
//
//        //when
//        JdbcUserDao userDao = new JdbcUserDao();
//        userDao.addUser(user);
//
//        //then
//        verify(statement, times(1)).executeUpdate();
//    }
//}