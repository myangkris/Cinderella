package com.cinderella.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    public static final int DUMMY_USER_ID = 1;
    @JsonProperty
	private final int id;
    @JsonProperty
	private final String username;
    @JsonProperty
	private String password;
    @JsonProperty
	private double balance;
    @JsonProperty
	private String phoneNumber;
    @JsonProperty
	private int bonusPoints;
    @JsonProperty
	private String email;
	
	private User(UserBuilder builder) {
		this.id = builder.id;
		this.username = builder.username;
		this.password = builder.password;
		this.balance = builder.balance;
		this.phoneNumber = builder.phoneNumber;
		this.bonusPoints = builder.bonusPoints;
		this.email = builder.email;
	}
	
	public static class UserBuilder {
		private int id;
		private String username;
		private String password;
		private double balance;
		private String phoneNumber;
		private int bonusPoints;
		private String email;
		
		public User build() {
			return new User(this);
		}
		
		public UserBuilder setId(int id) {
			this.id = id;
			return this;
		}
		
		public UserBuilder setUsername(String username) {
			this.username = username;
			return this;
		}
		
		public UserBuilder setPassword(String password) {
			this.password = password;
			return this;
		}
		
		public UserBuilder setBalance(double balance) {
			this.balance = balance;
			return this;
		}
		
		public UserBuilder setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
			return this;
		}
		
		public UserBuilder setBonusPoints(int bonusPoints) {
			this.bonusPoints = bonusPoints;
			return this;
		}
		
		public UserBuilder setEmail(String email) {
			this.email = email;
			return this;
		}
		
	}
	
	public int getId() {
		return id;
	}
		
	public String getUsername() {
		return username;
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
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public User setPhoneNumber(String phoneNumber) {
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
