package com.cinderella.dao.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class UserTableInitializer {

    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver").getConstructor().newInstance();
        Connection conn = DriverManager.getConnection(MySQLDBUtil.URL);
        
        Statement statement = conn.createStatement();
        String sql = "DROP TABLE IF EXISTS users";
        statement.executeUpdate(sql);
        
        sql = "CREATE TABLE users (id INT(8) NOT NULL auto_increment," + "username VARCHAR(255) NOT NULL," + "password VARCHAR(255) NOT NULL," + "balance DECIMAL(10,2),"
                + "phoneNumber varchar(15)," + "bonusPoints INT(8)," + "email VARCHAR(255),"
                + "PRIMARY KEY (id))";
        statement.executeUpdate(sql);
        
        // password -> 3229c1097c00d497a0fd282d586be050
        sql = "INSERT INTO users(username, password, balance, phoneNumber, bonusPoints, email) VALUES('Dummy_User', 'password', '100', '1112223344', '20','hello@yahoo.com')";
        statement.executeUpdate(sql);
        sql = "INSERT INTO users(username, password, balance, phoneNumber, bonusPoints, email) VALUES('smith', 'password', '100', '1112223344', '20','hello@yahoo.com')";
        statement.executeUpdate(sql);
        sql = "INSERT INTO users(username, password, balance, phoneNumber, bonusPoints, email) VALUES('John', 'password', '100', '1112223344', '20','hello@yahoo.com')";
        statement.executeUpdate(sql);
        sql = "INSERT INTO users(username, password, balance, phoneNumber, bonusPoints, email) VALUES('Pitt', 'password', '250', '2221114567', '20','hello@yahoo.com')";
        statement.executeUpdate(sql);
        sql = "INSERT INTO users(username, password, balance, phoneNumber, bonusPoints, email) VALUES('Doug', 'password', '12', '3331117895', '20','hello@yahoo.com')";
        statement.executeUpdate(sql);
        sql = "INSERT INTO users(username, password, balance, phoneNumber, bonusPoints, email) VALUES('John Doe', 'password', '100', '1597539875', '20','hello@yahoo.com')";
        statement.executeUpdate(sql);
        
        statement.close();
        conn.close();
        
        System.out.println("User table initialized!");
    }

}
