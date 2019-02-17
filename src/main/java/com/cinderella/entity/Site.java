package com.cinderella.entity;

public class Site {
	private String address;
	private int operatingHours;
	
	private Site(SiteBuilder sb) {
		this.address = sb.address;
		this.operatingHours = sb.operatingHours;
	}
	
	public static class SiteBuilder {
		private String address;
		private int operatingHours;
		
		public Site build() {
			return new Site(this);
		}
		
		public SiteBuilder setAddress(String address) {
			this.address = address;
			return this;
		}
		
		public SiteBuilder setOperatingHours(int operatingHours) {
			this.operatingHours = operatingHours;
			return this;
		}
	}
	
	public Site setAddress(String newAddress) {
		this.address = newAddress;
		return this;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public Site setOperatingHours(int newOperatingHours) {
		this.operatingHours = newOperatingHours;
		return this;
	}
	
	public int getOperatingHours() {
		return this.operatingHours;
	}
}
