package com.cinderella.dao.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cinderella.dao.DBConnection;
import com.cinderella.entity.Manager;
import com.cinderella.entity.Site;
import com.cinderella.entity.User;
import com.cinderella.entity.User.UserBuilder;
import com.cinderella.entity.WashMachine;


public class MySQLConnection implements DBConnection {
	private Connection conn;

	public MySQLConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getConstructor().newInstance();
			conn = DriverManager.getConnection(MySQLDBUtil.URL);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() {
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean verifyLogin(String userId, String password) {
		if (conn == null) {
			return false;
		}
		try {
			String sql = "SELECT UserId FROM user WHERE UserId = ? AND password = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, userId);
			statement.setString(2, password);

			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				return true;
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	/**
	 * Return User object by query the db using username
	 *
	 * 
	 * @param username
	 * @Return User
	 */
	@Override
	public User findUserByUsername(String username) {
		if (conn != null) {
			String sql = "SELECT * FROM user WHERE username = ?";
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, username);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					UserBuilder builder = new UserBuilder();
					builder.setUserId(rs.getInt("UserId"));
					builder.setUserName(rs.getString("username"));
					builder.setUserPassword(rs.getString("password"));
					builder.setUserBalance(rs.getInt("balance"));
					builder.setUserPhoneNumber(rs.getInt("phoneNumber"));
					builder.setUserBonusPoints(rs.getInt("bonusPoints"));
					builder.setUserEmail(rs.getString("email"));
					return builder.build();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	/*
	sql = "CREATE TABLE user (" + "UserId INT(7) NOT NULL," + "username VARCHAR(255) NOT NULL," + "password VARCHAR(255) NOT NULL," + "balance INT(9),"
						+ "phoneNumber INT(11)," + "bonusPoints INT(9)," + "email VARCHAR(255),"
						+ "PRIMARY KEY (UserId)" + ")";
	*/
	/**
	 * Return User object by query the db using userid
	 *
	 * 
	 * @param userid
	 * @Return User
	 */
	@Override
	public User findUserByUserId(int userid) {
		if (conn != null) {
			String sql = "SELECT * FROM user WHERE UserId = ?";
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, userid);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					UserBuilder builder = new UserBuilder();
					builder.setUserId(rs.getInt("UserId"));
					builder.setUserName(rs.getString("username"));
					builder.setUserPassword(rs.getString("password"));
					builder.setUserBalance(rs.getInt("balance"));
					builder.setUserPhoneNumber(rs.getInt("phoneNumber"));
					builder.setUserBonusPoints(rs.getInt("bonusPoints"));
					builder.setUserEmail(rs.getString("email"));
					return builder.build();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * Return Manager object by query the db using employeeAccountId
	 *
	 * 
	 * @param employeeAccountId
	 * @Return Manager
	 */
	@Override
	public Manager findManagerById(int employeeAccountId) {
		return null;
		
	}
	
	/**
	 * Return Site object by query the db using address
	 *
	 * 
	 * @param address
	 * @Return Site
	 */
	@Override
	public Site findSiteByAddress(String address) {
		return null;
		
	}
	
	/**
	 * Return WashMachine object by query the db using machineId
	 *
	 * 
	 * @param machineId
	 * @Return WashMachine
	 */
	@Override
	public WashMachine findWashMachineById(long machineId) {
		return null;
		
	}

}

