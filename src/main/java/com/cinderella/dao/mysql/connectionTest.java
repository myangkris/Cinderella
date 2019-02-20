package com.cinderella.dao.mysql;

import com.cinderella.dao.DBConnection;
import com.cinderella.dao.DBConnectionFactory;
import com.cinderella.entity.Site;

public class connectionTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DBConnection connection = DBConnectionFactory.getConnection();
		Site site = connection.findSiteByAddress("1234 Center Dr");
		System.out.printf("test 1: site is %s, %d\n", site.getAddress(), site.getOperatingHours());
		
		site = connection.findSiteByAddress("5647 Lin Road");
		if (site == null) {
			System.out.println("No such site");
		} else {
			System.out.printf("test 2: site is %s, %d\n", site.getAddress(), site.getOperatingHours());
		}
	}

}
