package com.cinderella.dao.mysql;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Calendar;
import java.sql.Connection;

public class MySQLTableCreation {
	// Run this as Java application to reset db schema.
	public static void main(String[] args) {
		try {
			// Step 1 Connect to MySQL.
			System.out.println("Connecting to " + MySQLDBUtil.URL);
			Class.forName("com.mysql.cj.jdbc.Driver").getConstructor().newInstance();
			Connection conn = DriverManager.getConnection(MySQLDBUtil.URL);

			if (conn == null) {
				return;
			}
			
			// Step 2 Drop tables in case they exist.
			Statement statement = conn.createStatement();
			String sql;

			sql = "DROP TABLE IF EXISTS reportdefect";
			statement.executeUpdate(sql);
			
			sql = "DROP TABLE IF EXISTS manage";
			statement.executeUpdate(sql);
			
			sql = "DROP TABLE IF EXISTS washmachine";
			statement.executeUpdate(sql);
			
			sql = "DROP TABLE IF EXISTS user";
			statement.executeUpdate(sql);
			
			sql = "DROP TABLE IF EXISTS manager";
			statement.executeUpdate(sql);
			
			sql = "DROP TABLE IF EXISTS site";
			statement.executeUpdate(sql);
			


			// Step 3 Create new tables
//			User, Manager, Site, WashMachine, ReportDefect, Manage 
			
			sql = "CREATE TABLE user (" + "UserId INT(7) NOT NULL auto_increment," + "username VARCHAR(255) NOT NULL," + "password VARCHAR(255) NOT NULL," + "balance INT(9),"
					+ "phoneNumber INT(11)," + "bonusPoints INT(9)," + "email VARCHAR(255),"
					+ "PRIMARY KEY (UserId)" + ")";
			statement.executeUpdate(sql);

			sql = "CREATE TABLE manager (" + "EmployeeAccountNumber INT(8) NOT NULL," + "password VARCHAR(255) NOT NULL,"
					+ "accessPreviledge INT(2)," + "PRIMARY KEY (EmployeeAccountNumber)" + ")";
			statement.executeUpdate(sql);

			sql = "CREATE TABLE site (" + "Address VARCHAR(255) NOT NULL," + "OperatingHours INT(2),"
					+ "PRIMARY KEY (Address)" + ")";
			statement.executeUpdate(sql);

			sql = "CREATE TABLE washmachine (" 
					+ "MachineID INT(13) NOT NULL," 
					+ "status INT(255) NOT NULL,"
					+ "pricePerService FLOAT(4,2),"
					+ "UsedBy INT(7),"
					+ "locatedAt VARCHAR(255),"
					+ "WaitedBy INT(7),"
					+ "startsAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,"
					+ "waitingCapacity INT(2),"
					+ "PRIMARY KEY (MachineID)," 
					+ "FOREIGN KEY (UsedBy) REFERENCES user(UserId),"
					+ "FOREIGN KEY (locatedAt) REFERENCES site(Address)," 
					+ "FOREIGN KEY (WaitedBy) REFERENCES user(UserId)" 
					+ ")";
			statement.executeUpdate(sql);
			
			sql = "CREATE TABLE reportdefect ("
					+ "ReportID INT(9),"
					+ "UserId INT(7),"
					+ "MachineID INT(13) NOT NULL,"
					+ "EmployeeAccountNumber INT(8),"
					+ "resolvedAtTime TIMESTAMP,"
					+ "PRIMARY KEY (ReportID),"
					+ "FOREIGN KEY (UserId) REFERENCES user(UserId),"
					+ "FOREIGN KEY (MachineID) REFERENCES washmachine(MachineID)," 
					+ "FOREIGN KEY (EmployeeAccountNumber) REFERENCES manager(EmployeeAccountNumber)" 
					+ ")";
			statement.executeUpdate(sql);
			
			sql = "CREATE TABLE manage ("
					+ "EmployeeAccountNumber INT(8) NOT NULL,"
					+ "MachineID INT(13) NOT NULL,"
					+ "PRIMARY KEY (EmployeeAccountNumber, MachineID),"
					+ "FOREIGN KEY (EmployeeAccountNumber) REFERENCES manager(EmployeeAccountNumber),"
					+ "FOREIGN KEY (MachineID) REFERENCES washmachine(MachineID)" 
					+ ")";
			statement.executeUpdate(sql);
			
			// Step 4: insert fake user 1111/3229c1097c00d497a0fd282d586be050
			sql = "INSERT INTO user(username, password, balance, phoneNumber, bonusPoints, email) VALUES('John', '3229c1097c00d497a0fd282d586be050', '100', '1112223344', '20','hello@yahoo.com')";
			statement.executeUpdate(sql);
			sql = "INSERT INTO user(username, password, balance, phoneNumber, bonusPoints, email) VALUES('Pitt', '3229c1097c00d497a0fd282d586be050', '100', '1112223344', '20','hello@yahoo.com')";
			statement.executeUpdate(sql);
			sql = "INSERT INTO user(username, password, balance, phoneNumber, bonusPoints, email) VALUES('Doug', '3229c1097c00d497a0fd282d586be050', '100', '1112223344', '20','hello@yahoo.com')";
			statement.executeUpdate(sql);
			sql = "INSERT INTO user(username, password, balance, phoneNumber, bonusPoints, email) VALUES('John Doe', '3229c1097c00d497a0fd282d586be050', '100', '1112223344', '20','hello@yahoo.com')";
			statement.executeUpdate(sql);
			
			sql = "INSERT INTO site VALUES('1234 Center Dr', '24')";
			statement.executeUpdate(sql);
			sql = "INSERT INTO site VALUES('Somewhere', '24')";
			statement.executeUpdate(sql);
			
			Calendar calendar = Calendar.getInstance();
		    java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());
		    
//			INSERT INTO `washmachine` (`MachineID`, `status`, `pricePerService`, `UsedBy`, `locatedAt`, `WaitedBy`, `startsAt`, `waitingCapacity`) VALUES ('233', '2', '2.1', '1111', '1234 Center Dr', NULL, CURRENT_TIMESTAMP, '1')
			sql = "INSERT INTO washmachine(MachineID, status, pricePerService, UsedBy, locatedAt, startsAt, waitingCapacity) VALUES('233', '3', '2.1', '1', '1234 Center Dr', ?, '1')";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
		    preparedStatement.setTimestamp(1, ourJavaTimestampObject);
		    preparedStatement.executeUpdate();

			conn.close();
			System.out.println("Import done successfully");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


