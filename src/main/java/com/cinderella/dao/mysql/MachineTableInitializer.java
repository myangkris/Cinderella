package com.cinderella.dao.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class MachineTableInitializer {

    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver").getConstructor().newInstance();
        Connection conn = DriverManager.getConnection(MySQLDBUtil.URL);
        
        Statement statement = conn.createStatement();
        String sql = "DROP TABLE IF EXISTS machines";
        statement.executeUpdate(sql);
        
        sql = "CREATE TABLE machines (" 
                + "MachineID INT(13) NOT NULL," 
                + "status INT(255) NOT NULL,"
                + "pricePerService FLOAT(4,2),"
                + "UsedBy INT(7),"
                + "locatedAt VARCHAR(255),"
                + "WaitedBy INT(7),"
                + "startsAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,"
                + "waitingCapacity INT(2),"
                + "PRIMARY KEY (MachineID)," 
                + "FOREIGN KEY (UsedBy) REFERENCES users(id),"
                + "FOREIGN KEY (locatedAt) REFERENCES site(Address)," 
                + "FOREIGN KEY (WaitedBy) REFERENCES user(UserId)" 
                + ")";
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
