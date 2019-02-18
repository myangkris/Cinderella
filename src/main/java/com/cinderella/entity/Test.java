package com.cinderella.entity;

import com.cinderella.dao.DBConnectionFactory;
import com.cinderella.dao.mysql.MySQLConnection;
import com.cinderella.dao.mysql.MySQLDBUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Calendar;

import com.cinderella.dao.DBConnection;

public class Test {
	public static void main (String[] args) {
		System.out.println("Connecting to MySQL");
		MySQLConnection conn = new MySQLConnection();
		System.out.println("Connected to MySQL");
		
		//Test1: FindMachineById(666)
		System.out.println("----------------------------------");
		System.out.println("Test1: find machine by id '666'(not existed");
		WashMachine mac = conn.findWashMachineById(666);
		try {
			System.out.println("MachineID:             " + mac.getId());
			System.out.println("Status:                " + mac.getStatus());
			System.out.println("Price per use:         " + mac.getPricePerService());
			System.out.println("Is using by:           " + mac.getUsedBy());
			System.out.println("Location:              " + mac.getLocation());
			System.out.println("Is waited by:          " + mac.getWaitedBy());
			System.out.println("Latest used at:        " + mac.getStartsAt());
			System.out.println("Waiting line capacity: " + mac.getWaitingCapacity());
		} catch (Exception e) {
			if (mac == null) {
				System.out.println("Machine not found");
			}
			System.out.println("Error messeage: " + e);
		}
		
		//Test2: FindMachineById(233)
		System.out.println("----------------------------------");
		System.out.println("Test2: find machine by id '233' ");
		mac = conn.findWashMachineById(233);
		
		try {
			System.out.println("MachineID:             " + mac.getId());
			System.out.println("Status:                " + mac.getStatus());
			System.out.println("Price per use:         " + mac.getPricePerService());
			System.out.println("Is using by:           " + mac.getUsedBy());
			System.out.println("Location:              " + mac.getLocation());
			System.out.println("Is waited by:          " + mac.getWaitedBy());
			System.out.println("Latest used at:        " + mac.getStartsAt());
			System.out.println("Waiting line capacity: " + mac.getWaitingCapacity());
		} catch (Exception e) {
			if (mac == null) {
				System.out.println("Machine not found");
			}
			System.out.println(e);
		}
		
		//Test3: Insert new washMachine(666)
		System.out.println("----------------------------------");
		System.out.println("Testing: creat machine id: 666");
		Calendar calendar = Calendar.getInstance();
	    java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());
		mac = new WashMachine.WashMachineBuilder()
				.setId(666)
				.setStatus(1)
				.setPricePerService((float) 3.0)
				.setUsedBy(null)
				.setLocation("Somewhere")
				.setWaitedBy(null)
				.setStartsAt(ourJavaTimestampObject)
				.setWaitingCapacity(2)
				.build();
		try {
			if (conn.updateWashMachine(mac)) {
				System.out.println("Insert successfully");
			} else {
				System.out.println("Insert failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Check: FindMachineById(666)
		System.out.println("----------------------------------");
		System.out.println("Check: find the created machine id '666'");
		mac = conn.findWashMachineById(666);
		try {
			System.out.println("MachineID:             " + mac.getId());
			System.out.println("Status:                " + mac.getStatus());
			System.out.println("Price per use:         " + mac.getPricePerService());
			System.out.println("Is using by:           " + mac.getUsedBy());
			System.out.println("Location:              " + mac.getLocation());
			System.out.println("Is waited by:          " + mac.getWaitedBy());
			System.out.println("Latest used at:        " + mac.getStartsAt());
			System.out.println("Waiting line capacity: " + mac.getWaitingCapacity());
		} catch (Exception e) {
			if (mac == null) {
				System.out.println("Machine not found");
			}
			System.out.println("Error messeage: " + e);
		}
		
		
		//Test4: Update an existed washMachine(233)
		System.out.println("----------------------------------");
		System.out.println("Test4: update machine id: 233");
		mac = new WashMachine.WashMachineBuilder()
				.setId(233)
				.setStatus(2)
				.setPricePerService((float) 2.8)
				.setUsedBy(null)
				.setLocation(null)
				.setWaitedBy(3333)
				.setStartsAt(ourJavaTimestampObject)
				.setWaitingCapacity(3)
				.build();
		try {
			if (conn.updateWashMachine(mac)) {
				System.out.println("Update successfully");
			} else {
				System.out.println("Update failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Check: FindMachineById(233)
		System.out.println("----------------------------------");
		System.out.println("Check: find the updated machine id '233' ");
		mac = conn.findWashMachineById(233);

		try {
			System.out.println("MachineID:             " + mac.getId());
			System.out.println("Status:                " + mac.getStatus());
			System.out.println("Price per use:         " + mac.getPricePerService());
			System.out.println("Is using by:           " + mac.getUsedBy());
			System.out.println("Location:              " + mac.getLocation());
			System.out.println("Is waited by:          " + mac.getWaitedBy());
			System.out.println("Latest used at:        " + mac.getStartsAt());
			System.out.println("Waiting line capacity: " + mac.getWaitingCapacity());
		} catch (Exception e) {
			if (mac == null) {
				System.out.println("Machine not found");
			}
			System.out.println(e);
		}
		
		// Test5: DeleteMachineById(666)
		System.out.println("----------------------------------");
		System.out.println("Test5: delete machine id '666' ");
		try {
			conn.deleteWashMachineById(666, false);
		} catch (Exception e) {
			System.out.println("Deletion failed");
			e.printStackTrace();
		}
		// Check: FindMachineById(666)
		System.out.println("----------------------------------");
		System.out.println("Check: find the created machine id '666'");
		mac = conn.findWashMachineById(666);
		try {
			System.out.println("MachineID:             " + mac.getId());
			System.out.println("Status:                " + mac.getStatus());
			System.out.println("Price per use:         " + mac.getPricePerService());
			System.out.println("Is using by:           " + mac.getUsedBy());
			System.out.println("Location:              " + mac.getLocation());
			System.out.println("Is waited by:          " + mac.getWaitedBy());
			System.out.println("Latest used at:        " + mac.getStartsAt());
			System.out.println("Waiting line capacity: " + mac.getWaitingCapacity());
		} catch (Exception e) {
			if (mac == null) {
				System.out.println("Machine not found");
			}
			System.out.println("Error messeage: " + e);
		} finally {
			conn.close();
		}

	}
}
