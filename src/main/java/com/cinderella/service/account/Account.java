package com.cinderella.service.account;

import org.json.JSONObject;

import com.cinderella.dao.DBConnection;
import com.cinderella.dao.DBConnectionFactory;
import com.cinderella.entity.User;

public abstract class Account {
	
	// set name 
	// set address 
	// set email
	// set phoneNumber
	// set password
	public static boolean logIn(String userName, String password) {
		DBConnection connection = DBConnectionFactory.getConnection();
		boolean logined = connection.verifyLogin(userName, password);
		connection.close();
		return logined;
	};
	
	public static boolean register(User user) {
		DBConnection connection = DBConnectionFactory.getConnection();
		if (connection.findUserByUsername(user.getUsername()) == null) {
			boolean registered = connection.addUser(user);
			connection.close();
			System.out.println("add already done");
			return registered;
		} 
		return false;
	};
	
	public abstract boolean updateProfile(User user);
	
	public abstract JSONObject getProfile();
	
}