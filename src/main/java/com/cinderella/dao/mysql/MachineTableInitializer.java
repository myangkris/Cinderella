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
                + "id INT(13) NOT NULL auto_increment," 
                + "status INT(255) NOT NULL,"
                + "pricePerService FLOAT(4,2),"
                + "usedBy INT(8),"
                + "locatedAt VARCHAR(255),"
                + "waitedBy INT(8),"
                + "startsAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,"
                + "waitingCapacity INT(3),"
                + "PRIMARY KEY (id)," 
                + "FOREIGN KEY (usedBy) REFERENCES users(id),"
                + "FOREIGN KEY (locatedAt) REFERENCES site(Address)," 
                + "FOREIGN KEY (waitedBy) REFERENCES users(id)" 
                + ")";
        statement.executeUpdate(sql);
        
        // password -> 3229c1097c00d497a0fd282d586be050
        for (int i = 0; i < 16; i++) {
            sql = "INSERT INTO machines (status, pricePerService, usedBy, locatedAt, waitedBy, waitingCapacity) "
                    + "VALUES(0, 2.00, 1, '1234 Center Dr', 1, 1)";
            statement.executeUpdate(sql);
        }
        
        statement.close();
        conn.close();
        
        System.out.println("Machines table initialized!");
    }

}
