package com.cinderella.service.account;

import com.cinderella.dao.DBConnection;
import com.cinderella.dao.DBConnectionFactory;
import com.cinderella.dao.mysql.MySQLConnection;
import com.cinderella.entity.User;
import com.cinderella.entity.User.UserBuilder;
import com.cinderella.service.account.Transaction;

public class UserAccount extends Account implements Transaction{
	private User user;
	DBConnection connection;
	
	public UserAccount(String userName) {
		this.connection = DBConnectionFactory.getConnection();
		this.user = getUser(userName);
	}
	
	private User getUser(String userName) {
		return connection.findUserByUsername(userName);
	} 
	
	public boolean report (String error) {
		return false;
	}
	
	@Override
	public int checkBalance() {
		// TODO Auto-generated method stub
		return user.getBalance();
	}

	@Override
	public boolean pay() {
		// TODO Auto-generated method stub
		if (user.getBalance() >= 2) {
			UserBuilder builder = new UserBuilder();
			builder.setUserId(user.getId());
			builder.setUserName(user.getName());
			builder.setUserPassword(user.getPassword());
			builder.setUserBalance(user.getBalance() - 2);
			builder.setUserPhoneNumber(user.getPhoneNumber());
			builder.setUserBonusPoints(user.getBonusPoints());
			builder.setUserEmail(user.getEmail());
			return connection.updateUser(builder.build());
		}
		return false;
	}

	@Override
	public int checkBonus() {
		// TODO Auto-generated method stub
		return user.getBonusPoints();
	}

	@Override
	public boolean refill(int money) {
		// TODO Auto-generated method stub
		UserBuilder builder = new UserBuilder();
		builder.setUserId(user.getId());
		builder.setUserName(user.getName());
		builder.setUserPassword(user.getPassword());
		builder.setUserBalance(user.getBalance() + money);
		builder.setUserPhoneNumber(user.getPhoneNumber());
		builder.setUserBonusPoints(user.getBonusPoints());
		builder.setUserEmail(user.getEmail());
		return connection.updateUser(builder.build());
	}

	@Override
	public boolean updateProfile(User updateUser) {
		// TODO Auto-generated method stub
		UserBuilder builder = new UserBuilder();
		builder.setUserId(user.getId());
		builder.setUserName(user.getName());
		builder.setUserPassword(updateUser.getPassword() == null ? user.getPassword() : updateUser.getPassword());
		builder.setUserBalance(user.getBalance());
		builder.setUserPhoneNumber(updateUser.getPhoneNumber() == 0 ? user.getPhoneNumber() : updateUser.getPhoneNumber());
		builder.setUserBonusPoints(user.getBonusPoints());
		builder.setUserEmail(updateUser.getEmail() == null ? user.getEmail() : updateUser.getEmail());
		return connection.updateUser(builder.build());
	}

	@Override
	public User getProfile() {
		// TODO Auto-generated method stub
		return user;
	}
	
//	public static void main(String arg[]) {
//		String userName = "John";
//		UserAccount useraccount = new UserAccount(userName);
//		System.out.println("start");
//		useraccount.pay();
//	}

}