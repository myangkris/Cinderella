package com.cinderella.dao.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cinderella.dao.DBConnection;
import com.cinderella.entity.Manager;
import com.cinderella.entity.Manager.ManagerBuilder;
import com.cinderella.entity.Site;
import com.cinderella.entity.Site.SiteBuilder;
import com.cinderella.entity.User;
import com.cinderella.entity.User.UserBuilder;
import com.cinderella.entity.WashMachine;
import com.cinderella.entity.WashMachine.WashMachineBuilder;;


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
	public boolean verifyLogin(String username, String password) {
		if (conn == null) {
			return false;
		}
		try {
			String sql = "SELECT username FROM user WHERE username = ? AND password = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
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
	public Manager findManagerById(int EmployeeAccountNumber) {
		if (conn != null) {
			String sql = "SELECT * FROM manager WHERE EmployeeAccountNumber = ?";
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, EmployeeAccountNumber);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					ManagerBuilder builder = new ManagerBuilder();
					builder.setEmployeeAccountNumber(EmployeeAccountNumber)
							.setPassword(rs.getString("password"))
							.setAccessPreviledge(rs.getInt("accessPreviledge"));
					return builder.build();
				}
			} catch (SQLException e) {
				System.out.println("Not connected to MySQL");
				e.printStackTrace();
			}
		}
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
		if (conn != null) {
			String sql = "SELECT * FROM site WHERE Address = ?";
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, address);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					SiteBuilder builder = new SiteBuilder();
					builder.setAddress(address)
							.setOperatingHours(rs.getInt("OperatingHours"));
					return builder.build();
				}
			} catch (SQLException e) {
				System.out.println("Not connected to MySQL");
				e.printStackTrace();
			}
		}
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
		if (conn != null) {
			String sql = "SELECT * FROM washmachine WHERE MachineId = ?";
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setLong(1, machineId);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					WashMachineBuilder builder = new WashMachineBuilder();
					builder.setId(rs.getLong("MachineId"));
					builder.setStatus(rs.getInt("status"));
					builder.setPricePerService(rs.getFloat("pricePerService"));
					builder.setUsedBy(rs.getInt("UsedBy"));
					builder.setLocation(rs.getString("locatedAt"));
					builder.setWaitedBy(rs.getInt("WaitedBy"));
					builder.setStartsAt(rs.getString("startsAt"));
					builder.setWaitingCapacity(rs.getInt("waitingCapacity"));
					return builder.build();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Not connected to MySQL");
				e.printStackTrace();
			}
		}
		return null;
		
	}

	/**
	 * Return success or not by connecting the db to update the user
	 *
	 *
	 * @param user
	 * @Return boolean, success or not
	 */
	@Override
	public boolean AddOrUpdateUser(User user) {
		// TO DO
		return false;
	}

	/**
	 * Return success or not by connecting the db to update the washMachine
	 *
	 *
	 * @param washMachine
	 * @Return boolean, success or not
	 */
	@Override
	public boolean AddOrUpdateWashMachine(WashMachine washMachine) {
		// TO DO
		return false;
	}

	/**
	 * Return success or not by connecting the db to update the site
	 *
	 *
	 * @param site
	 * @Return boolean, success or not
	 */
	@Override
	public boolean AddOrUpdateSite(Site site) {
		if (site == null) {
			return false;
		}
		Site here = findSiteByAddress(site.getAddress());
		int res;
		if (here != null) { // check if the site address exists in db
			res = updateSite(site);
		} else {
			res = addSite(site);
		}
		System.out.println(res);
		return res != 0;
	}
	
	private int addSite(Site site) {
		String sql = "INSERT INTO site VALUES (?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, site.getAddress());
			ps.setInt(2, site.getOperatingHours());
			int res = ps.executeUpdate();
			return res;
		} catch (SQLException e) {
			System.out.println("Cannot add into site");
			e.printStackTrace();
		}
		return 0;
	}
	
	private int updateSite(Site site) {
		String sql = "UPDATE site SET OperatingHours = ? WHERE Address = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, site.getOperatingHours());
			ps.setString(2, site.getAddress());
			int res = ps.executeUpdate();
			return res;
		} catch (SQLException e) {
			System.out.println("Cannot update site");
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * Return success or not by connecting the db to update the manager
	 *
	 *
	 * @param manager
	 * @Return boolean, success or not
	 */
	@Override
	public boolean AddOrUpdateManger(Manager manager) {
		if (manager == null) {
			return false;
		}
		Manager man = findManagerById(manager.getEmployeeAccountNumber());
		int res;
		if (man != null) { // check if the manager id exists in db
			res = updateManager(manager);
		} else {
			res = addManager(manager);
		}
		return res != 0;
	}
	
	private int addManager(Manager manager) {
		String sql = "INSERT INTO manager VALUES (?, ?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, manager.getEmployeeAccountNumber());
			ps.setString(2, manager.getPassword());
			ps.setInt(3, manager.getAccessPreviledge());
			int res = ps.executeUpdate();
			return res;
		} catch (SQLException e) {
			System.out.println("Cannot add into manager");
			e.printStackTrace();
		}
		return 0;
	}
	
	private int updateManager(Manager manager) {
		String sql = "UPDATE manager SET password = ?, accessPreviledge = ? WHERE EmployeeAccountNumber = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, manager.getPassword());
			ps.setInt(2, manager.getAccessPreviledge());
			ps.setInt(3, manager.getEmployeeAccountNumber());
			int res = ps.executeUpdate();
			return res;
		} catch (SQLException e) {
			System.out.println("Cannot update manager");
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * delete a row of user by connecting the db to find it first and then delete it if exists.
	 * if not exists, do nothing!!! DO NOT THROW EXCEPTIONS!!! in this case.
	 * Throws fail to delete exceptions if there is some dependency such as foreign key issue happened and causing the deletion failed.
	 *
	 * @param userid
	 * @param onCascade if set to be true, foreign key will be delete on cascade to meet the constrain. If set to be false, don't delete on cascade.
	 * @throws Exception if delete failed.
	 */
	@Override
	public void deleteUserById(int userid, boolean onCascade) throws Exception {

	}

	/**
	 * delete a row of wash machine by connecting the db to find it first and then delete it if exists.
	 * if not exists, do nothing!!! DO NOT THROW EXCEPTIONS!!! in this case.
	 * Throws fail to delete exceptions if there is some dependency such as foreign key issue happened and causing the deletion failed.
	 *
	 * @param machineId
	 * @param onCascade if set to be true, foreign key will be delete on cascade to meet the constrain. If set to be false, don't delete on cascade.
	 * @throws Exception if delete failed.
	 */
	@Override
	public void deleteWashMachineById(long machineId, boolean onCascade) throws Exception {

	}

	/**
	 * delete a row of site by connecting the db to find it first and then delete it if exists.
	 * if not exists, do nothing!!! DO NOT THROW EXCEPTIONS!!! in this case.
	 * Throws fail to delete exceptions if there is some dependency such as foreign key issue happened and causing the deletion failed.
	 *
	 * @param address
	 * @param onCascade if set to be true, foreign key will be delete on cascade to meet the constrain. If set to be false, don't delete on cascade.
	 * @throws Exception if delete failed.
	 */
	@Override
	public void deleteSiteByAddress(String address, boolean onCascade) throws Exception {
		if (onCascade) {
			throw new Exception("Not supported yet. Please delete site in a correct order.");
		} else {
			String sql = "DELETE FROM site WHERE Address = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, address);
			boolean res = ps.execute();
			if (!res) {
				System.out.println("delete site by address might fail.");
			}
		}
	}

	/**
	 * delete a row of manager by connecting the db to find it first and then delete it if exists.
	 * if not exists, do nothing!!! DO NOT THROW EXCEPTIONS!!! in this case.
	 * Throws fail to delete exceptions if there is some dependency such as foreign key issue happened and causing the deletion failed.
	 *
	 * @param employeeAccountId
	 * @param onCascade if set to be true, foreign key will be delete on cascade to meet the constrain. If set to be false, don't delete on cascade.
	 * @throws Exception if delete failed.
	 */
	@Override
	public void deleteManagerById(int employeeAccountNumber, boolean onCascade) throws Exception {
		if (onCascade) {
			throw new Exception("Not supported yet. Please delete manager in a correct order.");
		} else {
			String sql = "DELETE FROM manager WHERE EmployeeAccountNumber = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, employeeAccountNumber);
			boolean res = ps.execute();
			if (!res) {
				System.out.println("delete manager by EmployeeAccountNumber might fail.");
			}
		}
	}

}

