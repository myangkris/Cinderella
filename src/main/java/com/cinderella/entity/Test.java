package com.cinderella.entity;

import com.cinderella.dao.mysql.MySQLConnection;

public class Test {
	public static void main (String[] args) {
		System.out.println("Connecting to MySQL");
		MySQLConnection conn = new MySQLConnection();
		System.out.println("Connected to MySQL");
		
		System.out.println("----------------------------------");
		System.out.println("Testing: find machine by id '666' ");
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
		
		System.out.println("----------------------------------");
		System.out.println("Testing: find machine by id '233' ");
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
		} finally {
			conn.close();
		}
	}
}
