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
	 * @param username
	 * @param password
	 * @return boolean
	 */
	public boolean verifyLogin(String username, String password);
	
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

	/**
	 * Return success or not by connecting the db to update the user
	 *
	 *
	 * @param user
	 * @Return boolean, success or not
	 */
	public boolean updateUser(User user);

	/**
	 * Return success or not by connecting the db to update the washMachine
	 *
	 *
	 * @param washMachine
	 * @Return boolean, success or not
	 */
	public boolean updateWashMachine(WashMachine washMachine);

	/**
	 * Return success or not by connecting the db to update the site
	 *
	 *
	 * @param site
	 * @Return boolean, success or not
	 */
	public boolean updateSite(Site site);

	/**
	 * Return success or not by connecting the db to update the manager
	 *
	 *
	 * @param manager
	 * @Return boolean, success or not
	 */
	public boolean updateManger(Manager manager);

	/**
	 * delete a row of user by connecting the db to find it first and then delete it if exists.
	 * if not exists, do nothing!!! DO NOT THROW EXCEPTIONS!!! in this case.
	 * Throws fail to delete exceptions if there is some dependency such as foreign key issue happened and causing the deletion failed.
	 *
	 * @param userid
	 * @param onCascade if set to be true, foreign key will be delete on cascade to meet the constrain. If set to be false, don't delete on cascade.
	 * @throws Exception if delete failed.
	 */
	public void deleteUserById(int userid, boolean onCascade) throws Exception;

	/**
	 * delete a row of wash machine by connecting the db to find it first and then delete it if exists.
	 * if not exists, do nothing!!! DO NOT THROW EXCEPTIONS!!! in this case.
	 * Throws fail to delete exceptions if there is some dependency such as foreign key issue happened and causing the deletion failed.
	 *
	 * @param machineId
	 * @param onCascade if set to be true, foreign key will be delete on cascade to meet the constrain. If set to be false, don't delete on cascade.
	 * @throws Exception if delete failed.
	 */
	public void deleteWashMachineById(long machineId, boolean onCascade) throws Exception;

	/**
	 * delete a row of site by connecting the db to find it first and then delete it if exists.
	 * if not exists, do nothing!!! DO NOT THROW EXCEPTIONS!!! in this case.
	 * Throws fail to delete exceptions if there is some dependency such as foreign key issue happened and causing the deletion failed.
	 *
	 * @param address
	 * @param onCascade if set to be true, foreign key will be delete on cascade to meet the constrain. If set to be false, don't delete on cascade.
	 * @throws Exception if delete failed.
	 */
	public void deleteSiteByAddress(String address, boolean onCascade) throws Exception;

	/**
	 * delete a row of manager by connecting the db to find it first and then delete it if exists.
	 * if not exists, do nothing!!! DO NOT THROW EXCEPTIONS!!! in this case.
	 * Throws fail to delete exceptions if there is some dependency such as foreign key issue happened and causing the deletion failed.
	 *
	 * @param employeeAccountId
	 * @param onCascade if set to be true, foreign key will be delete on cascade to meet the constrain. If set to be false, don't delete on cascade.
	 * @throws Exception if delete failed.
	 */
	public void deleteManagerById(int employeeAccountId, boolean onCascade) throws Exception;
}


