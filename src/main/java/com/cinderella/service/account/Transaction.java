package com.cinderella.service.account;


public interface Transaction {


	public int checkBalance();
	/**
	 * Return whether the credential is correct. (This is not needed for main
	 * course, just for demo and extension)
	 * Return 
	 * @param userId
	 * @param password
	 * @return boolean
	 */

	public boolean pay();
	/*
	 * if balance is enough 
	 * 	update balance , return ture 
	 * else return false 
	 */

	public int checkBonus();
	/*
	 * return userBonus
	 */

	public boolean refill(int money);
	/*
	 * update use balance
	 */

}