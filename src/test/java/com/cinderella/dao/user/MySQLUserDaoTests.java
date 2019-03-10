package com.cinderella.dao.user;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.cinderella.entity.User;
import com.cinderella.entity.User.UserBuilder;


public class MySQLUserDaoTests {
    private UserDao dao;
    
    public MySQLUserDaoTests() {
        try {
            dao = MySQLUserDao.class.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testAdd() throws Exception {
        UserBuilder builder = new UserBuilder();
        builder.setUsername("lis")
               .setPassword("password")
               .setBalance(100.00)
               .setPhoneNumber("12345678")
               .setBonusPoints(100)
               .setEmail("lis@cinderella.com");
        dao.add(builder.build());
    }
    
    @Test
    public void testDeleteUserById() throws Exception {
        dao.deleteUserById(8);
    }
    
    @Test
    public void tesUpdate() {
        User user = dao.getUserById(7);
        double origBalance = user.getBalance();
        user.setBalance(origBalance + 10);
        dao.update(user);
        user = dao.getUserById(7);
        assertEquals(origBalance + 10, user.getBalance(), 0.00001);
    }
    
    @Test
    public void testGetUserById() {
        User user = dao.getUserById(7);
        assertEquals(user.getUsername(), "lis");
    }
}
