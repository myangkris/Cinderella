package com.cinderella.entity;

public class Manager {
		private int employeeAccountNumber;
		private String password;
		private int accessPreviledge;
		
		private Manager(ManagerBuilder sb) {
			this.employeeAccountNumber = sb.employeeAccountNumber;
			this.password = sb.password;
			this.accessPreviledge = sb.accessPreviledge;
		}
		
		public static class ManagerBuilder {
			private int employeeAccountNumber;
			private String password;
			private int accessPreviledge;
			
			public Manager build() {
				return new Manager(this);
			}
			
			public ManagerBuilder setEmployeeAccountNumber(int employeeAccountNumber) {
				this.employeeAccountNumber = employeeAccountNumber;
				return this;
			}
			
			public ManagerBuilder setPassword(String password) {
				this.password = password;
				return this;
			}
			
			public ManagerBuilder setAccessPreviledge(int accessPreviledge) {
				this.accessPreviledge = accessPreviledge;
				return this;
			}
		}
		
		public Manager setEmployeeAccountNumber(int employeeAccountNumber) {
			this.employeeAccountNumber = employeeAccountNumber;
			return this;
		}
		
		public int getEmployeeAccountNumber() {
			return this.employeeAccountNumber;
		}
		
		public Manager setPassword(String password) {
			this.password = password;
			return this;
		}
		
		public String getPassword() {
			return this.password;
		}
		
		public Manager setAccessPreviledge(int accessPreviledge) {
			this.accessPreviledge = accessPreviledge;
			return this;
		}
		
		public int getAccessPreviledge() {
			return this.accessPreviledge;
		}
}
