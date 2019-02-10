package com.cinderella.dao;

import java.util.List;
import java.util.Set;

import com.cinderella.entity.Manager;
import com.cinderella.entity.Site;
import com.cinderella.entity.User;
import com.cinderella.entity.WashMachine;

public interface DBConnection {
	/**
	 * Close the connection.
	 */
	public void close();

	/**
	 * Return whether the credential is correct. (This is not needed for main
	 * course, just for demo and extension)
	 * 
	 * @param userId
	 * @param password
	 * @return boolean
	 */
	public boolean verifyLogin(String userId, String password);
	
	/**
	 * Return User object by query the db using username
	 *
	 * 
	 * @param username
	 * @Return User
	 */
	public User findUserByUsername(String username);
	
	/**
	 * Return User object by query the db using userid
	 *
	 * 
	 * @param userid
	 * @Return User
	 */
	public User findUserByUserId(int userid);
	
	/**
	 * Return Manager object by query the db using employeeAccountId
	 *
	 * 
	 * @param employeeAccountId
	 * @Return Manager
	 */
	public Manager findManagerById(int employeeAccountId);
	
	/**
	 * Return Site object by query the db using address
	 *
	 * 
	 * @param address
	 * @Return Site
	 */
	public Site findSiteByAddress(String address);
	
	/**
	 * Return WashMachine object by query the db using machineId
	 *
	 * 
	 * @param machineId
	 * @Return WashMachine
	 */
	public WashMachine findWashMachineById(long machineId);
}


