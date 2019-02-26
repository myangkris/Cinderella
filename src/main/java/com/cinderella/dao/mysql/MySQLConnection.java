package com.cinderella.dao.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cinderella.dao.DBConnection;
import com.cinderella.entity.Manager;
import com.cinderella.entity.Manager.ManagerBuilder;
import com.cinderella.entity.Site;
import com.cinderella.entity.Site.SiteBuilder;
import com.cinderella.entity.User;
import com.cinderella.entity.User.UserBuilder;
import com.cinderella.entity.WashMachine;
import com.cinderella.entity.WashMachine.WashMachineBuilder;;

@Repository
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
					builder.setUserBalance(rs.getDouble("balance"));
					builder.setUserPhoneNumber(rs.getLong("phoneNumber"));
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
					builder.setUserBalance(rs.getDouble("balance"));
					builder.setUserPhoneNumber(rs.getLong("phoneNumber"));
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
					builder.setEmployeeAccountNumber(EmployeeAccountNumber).setPassword(rs.getString("password"))
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
					builder.setAddress(address).setOperatingHours(rs.getInt("OperatingHours"));
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
					builder.setStartsAt(rs.getTimestamp("startsAt"));
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
	public boolean addOrUpdateUser(User user) {
		// TODO Auto-generated method stub
		return updateUser(user) || addUser(user);
	}

	@Override
	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		if (conn != null && user != null) {		
			String sql = "INSERT IGNORE INTO user("
					+ "UserId,"
					+ "username,"
					+ "password,"
					+ "balance,"
					+ "phoneNumber,"
					+ "bonusPoints,"
					+ "email) VALUES(?, ?, ?, ?, ?, ?, ?)";
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, user.getId());
				stmt.setString(2, user.getName());
				stmt.setString(3, user.getPassword());
				stmt.setDouble(4, user.getBalance());
				stmt.setLong(5, user.getPhoneNumber());
				stmt.setInt(6, user.getBonusPoints());
				stmt.setString(7, user.getEmail());
				String userIdString = Integer.toString(user.getId());
				if (stmt.executeUpdate() == 1) {
					System.out.println("User " + userIdString + " successfully added");
					return true;
				} else {
					System.out.println("User " + userIdString + " already exists. User addition exited.");
					return false;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	/*
	 * sql = "CREATE TABLE user (" + "UserId INT(7) NOT NULL auto_increment," +
	 * "username VARCHAR(255) NOT NULL," + "password VARCHAR(255) NOT NULL," +
	 * "balance DECIMAL(10,2)," + "phoneNumber DECIMAL(11, 0)," +
	 * "bonusPoints INT(8)," + "email VARCHAR(255)," + "PRIMARY KEY (UserId)" + ")";
	 */

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		if (conn != null && user != null) {
			String sql = "UPDATE user SET "
					+ "username = ?,"
					+ "password = ?,"
					+ "balance = ?,"
					+ "phoneNumber = ?,"
					+ "bonusPoints = ?,"
					+ "email = ? WHERE UserId = ?";
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, user.getName());
				stmt.setString(2, user.getPassword());
				stmt.setDouble(3, user.getBalance());
				stmt.setLong(4, user.getPhoneNumber());
				stmt.setInt(5, user.getBonusPoints());
				stmt.setString(6, user.getEmail());
				stmt.setInt(7, user.getId());
				String userIdString = Integer.toString(user.getId());
				if (stmt.executeUpdate() == 1) {
					System.out.println("User " + userIdString + " successfully updated.");
					return true;
				} else {
					System.out.println("User " + userIdString + " doesn't exist. Update exited.");
					return false;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
	public boolean addOrUpdateWashMachine(WashMachine washMachine) {
		if (conn == null) {
			System.err.println("DB connection failed");
			return false;
		}
		// check if the machine already existed
		WashMachine mac = findWashMachineById(washMachine.getId());

		if (mac != null) {
			System.out.println("Call update helper");
			return updateWashMachine(washMachine);
		} else {
			System.out.println("Call insert helper");
			return addWashMachine(washMachine);
		}
	}

	@Override
	public boolean addWashMachine(WashMachine washMachine) {
		if (conn == null) {
			System.err.println("DB connection failed");
			return false;
		}
		try {
			// id, status, pricePerService, usedBy, location, waitedBy, startsAt,
			// waitingCapacity;
			String sql = "INSERT INTO washmachine VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, washMachine.getId());
			ps.setInt(2, washMachine.getStatus());
			ps.setFloat(3, washMachine.getPricePerService());
			ps.setInt(4, washMachine.getUsedBy());
			if (washMachine.getLocation() != null) {
				ps.setString(5, washMachine.getLocation());
			} else {
				ps.setNull(5, java.sql.Types.VARCHAR);
			}
			ps.setInt(6, washMachine.getWaitedBy());
			ps.setTimestamp(7, washMachine.getStartsAt());
			ps.setInt(8, washMachine.getWaitingCapacity());

			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateWashMachine(WashMachine washMachine) {
		if (conn == null) {
			System.err.println("DB connection failed");
			return false;
		}
		try {
			// When creating a new wash machine with washMachineBuilder,
			// if the usedBy and waitedBy is not set, it will be 0 by default,
			// because in entity.washMachine, usedBy and waitedBy are int.
			// So fake user '0' is needed in user table to indicates 'no user'.
			
			// Explanation of why ps.setString(4, washMachine.getLocation()) is more complicated:
			// If location is not set, it will be null.
			// But we don't have fake address 'null'.
			// So before the location set to database, it needs to be checked.
			// If it is null, we set it null.
			
			String sql = "UPDATE washmachine SET "
					+ "status = ?, "
					+ "pricePerService = ?, " 
					+ "UsedBy = ?, " 
					+ "locatedAt = ?, " 
					+ "WaitedBy = ?, "
					+ "startsAt = ?, " 
					+ "waitingCapacity = ? " 
					+ "WHERE MachineId = ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, washMachine.getStatus());
			ps.setFloat(2, washMachine.getPricePerService());
			ps.setInt(3, washMachine.getUsedBy());
			if (washMachine.getLocation() != null) {
				ps.setString(4, washMachine.getLocation());
			} else {
				ps.setNull(4, java.sql.Types.VARCHAR);
			}
			ps.setInt(5, washMachine.getWaitedBy());
			ps.setTimestamp(6, washMachine.getStartsAt());
			ps.setInt(7, washMachine.getWaitingCapacity());
			ps.setLong(8, washMachine.getId());

			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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
	public boolean addOrUpdateSite(Site site) {
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
	public boolean addOrUpdateManger(Manager manager) {
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
		if (conn == null) {
			throw new SQLException("No DB connection. User deletion Failure.");
		}
		if (onCascade) {
			throw new Exception("Not supported yet. Please delete user in a correct order.");
		}
		String sql = "DELETE FROM user WHERE UserId = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, userid);
		String userIdString = Integer.toString(userid);
		if (stmt.executeUpdate() == 0) {
			sql = "SELECT UserId FROM user WHERE UserId = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, userid);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				throw new SQLException("Deletion Failure. User " + userIdString + " still exists.");
			} else {
				System.out.println("User " + userIdString + " doesn't exist. User deletion exited.");
			}
		} else {
			System.out.println("User " + userIdString + " successfully deleted.");
		}
	}

	/**
	 * delete a row of wash machine by connecting the db to find it first and then
	 * delete it if exists. if not exists, do nothing!!! DO NOT THROW EXCEPTIONS!!!
	 * in this case. Throws fail to delete exceptions if there is some dependency
	 * such as foreign key issue happened and causing the deletion failed.
	 *
	 * @param machineId
	 * @param onCascade if set to be true, foreign key will be delete on cascade to
	 *                  meet the constrain. If set to be false, don't delete on
	 *                  cascade.
	 * @throws Exception if delete failed.
	 */
	@Override
	public void deleteWashMachineById(long machineId, boolean onCascade) throws Exception {
		if (conn == null) {
			System.err.println("DB connection failed");
			return;
		}
		// check if the machine already existed
		WashMachine mac = findWashMachineById(machineId);

		if (mac != null) {
			try {
				System.out.println("Start to delete the machine");
				String sql = "DELETE FROM washmachine WHERE MachineID = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setLong(1, machineId);
				ps.execute();
				System.out.println("Successful deletion");
			} catch (Exception e) {
				System.out.println("error!");
				e.printStackTrace();
			}
		} else {
			System.out.println("The machine is not existed");
			return;
		}
	}

	/**
	 * delete a row of site by connecting the db to find it first and then delete it
	 * if exists. if not exists, do nothing!!! DO NOT THROW EXCEPTIONS!!! in this
	 * case. Throws fail to delete exceptions if there is some dependency such as
	 * foreign key issue happened and causing the deletion failed.
	 *
	 * @param address
	 * @param onCascade if set to be true, foreign key will be delete on cascade to
	 *                  meet the constrain. If set to be false, don't delete on
	 *                  cascade.
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
			if (res) {
				System.out.println("delete site by address might fail.");
			}
		}
	}

	/**
	 * delete a row of manager by connecting the db to find it first and then delete
	 * it if exists. if not exists, do nothing!!! DO NOT THROW EXCEPTIONS!!! in this
	 * case. Throws fail to delete exceptions if there is some dependency such as
	 * foreign key issue happened and causing the deletion failed.
	 *
	 * @param employeeAccountId
	 * @param onCascade         if set to be true, foreign key will be delete on
	 *                          cascade to meet the constrain. If set to be false,
	 *                          don't delete on cascade.
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
			if (res) {
				System.out.println(
						"delete manager by EmployeeAccountNumber might fail due to none such employeeAccountNumber in db.");
			}
		}
	}

	/**
	 * Get all the washing machines of the address. Go through the washmachine table
	 * and get all the rows WHERE locatedAt = address. If connection failed, return
	 * null. If no machines found, return empty list.
	 * 
	 * @param address
	 * @return List<WashMachine>
	 * @throws Exception
	 */
	@Override
	public List<WashMachine> getWashMachineList(String address) {
		if (conn == null) {
			System.err.println("DB connection failed");
			return null;
		}
		List<WashMachine> list = new ArrayList<>();
		String sql = "SELECT * FROM washmachine WHERE locatedAt = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, address);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				WashMachineBuilder builder = new WashMachineBuilder();
				builder.setId(rs.getLong("MachineId"));
				builder.setStatus(rs.getInt("status"));
				builder.setPricePerService(rs.getFloat("pricePerService"));
				builder.setUsedBy(rs.getInt("UsedBy"));
				builder.setLocation(rs.getString("locatedAt"));
				builder.setWaitedBy(rs.getInt("WaitedBy"));
				builder.setStartsAt(rs.getTimestamp("startsAt"));
				builder.setWaitingCapacity(rs.getInt("waitingCapacity"));
				list.add(builder.build());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Not connected to MySQL");
			e.printStackTrace();
		}
		return list;
	}

}
