package com.cinderella.entity;

public class User {
    public static final int DUMMY_USER_ID = 0;
	
	private final int id;
	private final String name;
	private String password;
	private double balance;
	private long phoneNumber;
	private int bonusPoints;
	private String email;
	
	private User(UserBuilder builder) {
		this.id = builder.userId;
		this.name = builder.userName;
		this.password = builder.userPassword;
		this.balance = builder.userBalance;
		this.phoneNumber = builder.userPhoneNumber;
		this.bonusPoints = builder.userBonusPoints;
		this.email = builder.userEmail;
	}
	
	public static class UserBuilder {
		private int userId;
		private String userName;
		private String userPassword;
		private double userBalance;
		private long userPhoneNumber;
		private int userBonusPoints;
		private String userEmail;
		
		public User build() {
			return new User(this);
		}
		
		public UserBuilder setUserId(int userId) {
			this.userId = userId;
			return this;
		}
		
		public UserBuilder setUserName(String userName) {
			this.userName = userName;
			return this;
		}
		
		public UserBuilder setUserPassword(String userPassword) {
			this.userPassword = userPassword;
			return this;
		}
		
		public UserBuilder setUserBalance(double userBalance) {
			this.userBalance = userBalance;
			return this;
		}
		
		public UserBuilder setUserPhoneNumber(long userPhoneNumber) {
			this.userPhoneNumber = userPhoneNumber;
			return this;
		}
		
		public UserBuilder setUserBonusPoints(int userBonusPoints) {
			this.userBonusPoints = userBonusPoints;
			return this;
		}
		
		public UserBuilder setUserEmail(String userEmail) {
			this.userEmail = userEmail;
			return this;
		}
		
	}
	
	public int getId() {
		return id;
	}
		
	public String getName() {
		return name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public User setPassword(String password) {
		this.password = password;
		return this;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public User setBalance(double balance) {
		this.balance = balance;
		return this;
	}
	
	public long getPhoneNumber() {
		return phoneNumber;
	}
	
	public User setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
		return this;
	}
	
	public int getBonusPoints() {
		return bonusPoints;
	}
	
	public User setBonusPoints(int bonusPoints) {
		this.bonusPoints = bonusPoints;
		return this;
	}
	
	public String getEmail() {
		return email;
	}
	
	public User setEmail(String email) {
		this.email = email;
		return this;
	}
	
}
