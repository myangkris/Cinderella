package com.cinderella.service.account;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cinderella.dao.DBConnection;
import com.cinderella.dao.DBConnectionFactory;
import com.cinderella.dao.mysql.MySQLConnection;
import com.cinderella.entity.User;
import com.cinderella.entity.WashMachine;
import com.cinderella.entity.User.UserBuilder;
import com.cinderella.service.account.Transaction;

public class UserAccount extends Account implements Transaction{
	final static String address = "1234 Center Dr";
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
	public JSONObject getProfile() {
		// TODO Auto-generated method stub
		JSONObject obj = new JSONObject();
					try {
						obj.put("name", user.getName());
						obj.put("balance", user.getBalance());
						obj.put("phone_number", user.getPhoneNumber());
						obj.put("bonus_point", user.getBonusPoints());
						obj.put("email", user.getEmail());
					} catch (JSONException e) {
						e.printStackTrace();
					}
		return obj;
	}
	
	public JSONArray checkMachineList() throws Exception {
		List<WashMachine> list =  connection.getWashMachineList(address);
		return toJSONArray(list);
	}
	
	private JSONArray toJSONArray(List<WashMachine> list) {
		JSONArray array = new JSONArray();
		for(WashMachine washmachine : list) {
			JSONObject obj = new JSONObject();
//`MachineID`, `status`, `pricePerService`, `UsedBy`, `locatedAt`, `WaitedBy`, `startsAt`, `waitingCapacity`
			try {
				obj.put("MachineID", washmachine.getId());
				obj.put("status", washmachine.getStatus());
				obj.put("pricePerService", washmachine.getPricePerService());
				obj.put("UsedBy", washmachine.getUsedBy());
				obj.put("locatedAt", washmachine.getLocation());
				obj.put("WaitedBy", washmachine.getWaitedBy());
				obj.put("startsAt", washmachine.getStartsAt());
				obj.put("waitingCapacity", washmachine.getWaitingCapacity());
				array.put(obj);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return array;
	}
//	public static void main(String arg[]) {
//		String userName = "John";
//		UserAccount useraccount = new UserAccount(userName);
//		System.out.println("start");
//		useraccount.pay();
//	}

}