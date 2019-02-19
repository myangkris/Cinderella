package com.cinderella.dao.mysql;

import com.cinderella.dao.DBConnection;
import com.cinderella.dao.DBConnectionFactory;
import com.cinderella.entity.Site;
import com.cinderella.entity.*;

public class connectionTest {

	public static void main(String[] args) {
		/* Site CRUD tests.
		 * 
		 * Test 1: find by id positive test.
		 * Test 2: find by id negative test.
		 * Test 3: add or update positive test.
		 * Test 4: delete positive test.
		 * Test 5: delete negative test.
		 */
		DBConnection connection = DBConnectionFactory.getConnection();
		
		// Test 1
		Site site = connection.findSiteByAddress("1234 Center Dr");
		if (site != null) {
			System.out.printf("Test 1 passed. Find by id success. Results:\nSite is %s, %d\n", site.getAddress(), site.getOperatingHours());
		} else {
		System.out.println("Test 1 failed. No such site.");
		}
		
		
		// Test 2
		site = connection.findSiteByAddress("5647 Lin Road");
		if (site == null) {
			System.out.println("Test 2 passed. Find by id success. Results:\nNo such site.");
		} else {
			System.out.printf("Test 2 falied. Results\n:\nSite is %s, %d\n", site.getAddress(), site.getOperatingHours());
		}
		
		
		// Test 3
		Site new_site = new Site.SiteBuilder()
				.setAddress("178 Build Dr")
				.setOperatingHours(10).build();
		boolean insertResult = connection.addOrUpdateSite(new_site);
		if (insertResult) {
			System.out.printf("Test 3 passed. Add success. Results:\n");
			site = connection.findSiteByAddress("178 Build Dr");
			System.out.printf("Site is %s, %d\n", site.getAddress(), site.getOperatingHours());
		}
		
		
		// Test 4
		try {
			connection.deleteSiteByAddress("178 Build Dr", false);
			
			site = connection.findSiteByAddress("178 Build Dr");
			if (site == null) {
				System.out.printf("Test 4 passed. Delete success.\n");
			} else {
				System.out.printf("Test 4 falied. Found results in db:\nSite is %s, %d\n", site.getAddress(), site.getOperatingHours());
			}
			
		} catch (Exception e) {
			System.out.printf("Test 4 falied due to unknown reason. See the stack trace log below.");
			e.printStackTrace();
		}
		
		// Test 5
		try {
			connection.deleteSiteByAddress("Nowhere", false);
			
			site = connection.findSiteByAddress("Nowhere");
			if (site == null) {
				System.out.printf("Test 5 passed. Delete success although there is no such record in db.\n");
			} else {
				System.out.printf("Test 5 falied. Found results in db:\nSite is %s, %d\n", site.getAddress(), site.getOperatingHours());
			}
			
		} catch (Exception e) {
			System.out.printf("Test 5 falied due to unknown reason. See the stack trace log below.");
			e.printStackTrace();
		}
		
		System.out.println("__________________________________________________________________________________________");
		System.out.println("Now it's the manager CRUD tests.");
		/* Manager CRUD tests.
		 * 
		 * Test 1: find by id negative test.
		 * Test 2: add or update positive test.
		 * Test 3: find by id positive test.
		 * Test 4: delete negative test.
		 * Test 5: delete positive test.
		 */
		// Test 1
		Manager mng = connection.findManagerById(87654321);
		if (mng != null) {
			System.out.printf("Test 1 failed. Found results:\nManager is %d, %s, %d\n", mng.getEmployeeAccountNumber(), mng.getPassword(), mng.getAccessPreviledge());
		} else {
			System.out.println("Test 1 passed. No such manager.");
		}
		
		
		// Test 2
		Manager new_mng = new Manager.ManagerBuilder()
				.setEmployeeAccountNumber(99999999)
				.setPassword("tianzhidao")
				.setAccessPreviledge(9).build();
		insertResult = connection.addOrUpdateManger(new_mng);
		if (insertResult) {
			System.out.printf("Test 2 passed. Add success. Results:\n");
		} else {
			System.out.printf("Test 2 fail, cannot insert into db\n");
		}
		
		
		// Test 3
		mng = connection.findManagerById(99999999);
		if (mng != null) {
			System.out.printf("Test 3 passed. Found results:\nManager is %d, %s, %d\n", mng.getEmployeeAccountNumber(), mng.getPassword(), mng.getAccessPreviledge());
		} else {
			System.out.println("Test 3 failed. Found nothing.");
		}
		
		
		// Test 4
		try {
			connection.deleteManagerById(12345678, false);
			
			mng = connection.findManagerById(12345678);
			if (site == null) {
				System.out.printf("Test 4 passed. Delete success although there is no such record in db.\n");
			} else {
				System.out.printf("Test 4 falied. Found results in db:\nManager is %d %s, %d\n", mng.getEmployeeAccountNumber(), mng.getPassword(), mng.getAccessPreviledge());
			}
			
		} catch (Exception e) {
			System.out.printf("Test 4 falied due to unknown reason. See the stack trace log below.");
			e.printStackTrace();
		}
		
		
		// Test 5
		try {
			connection.deleteManagerById(99999999, false);
			
			mng = connection.findManagerById(99999999);
			if (site == null) {
				System.out.printf("Test 5 passed. Delete success.\n");
			} else {
				System.out.printf("Test 5 falied. Found results in db:\nManager is %d, %s, %d\n", mng.getEmployeeAccountNumber(), mng.getPassword(), mng.getAccessPreviledge());
			}
			
		} catch (Exception e) {
			System.out.printf("Test 5 falied due to unknown reason. See the stack trace log below.");
			e.printStackTrace();
		}
	}

}
