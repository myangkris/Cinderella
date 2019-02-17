package com.cinderella.entity;

public class WashMachine {
	// Fields------------------------------------------------
	private final long id;
	private int status;
	private float pricePerService;
	private int usedBy;
	private String location;
	private int waitedBy;
	private String startsAt;
	private int waitingCapacity;
	
	// Constructor------------------------------------------------
	private WashMachine(WashMachineBuilder builder) {
		this.id = builder.id;
		this.status = builder.status;
		this.pricePerService = builder.pricePerService;
		this.usedBy = builder.usedBy;
		this.location = builder.location;
		this.waitedBy = builder.waitedBy;
		this.startsAt = builder.startsAt;
		this.waitingCapacity = builder.waitingCapacity;
	}

	// Getters------------------------------------------------
	public long getId() {
		return id;
	}
	public int getStatus() {
		return status;
	}
	
	public float getPricePerService() {
		return pricePerService;
	}
	
	public int getUsedBy() {
		return usedBy;
	}
	
	public String getLocation() {
		return location;
	}
	
	public int getWaitedBy() {
		return waitedBy;
	}
	
	public String getStartsAt() {
		return startsAt;
	}
	
	public int getWaitingCapacity() {
		return waitingCapacity;
	}
	
	// Setters------------------------------------------------	
	public WashMachine setStatus(int status) {
		this.status = status;
		return this;
	}

	public WashMachine setPricePerService(float pricePerService) {
		this.pricePerService = pricePerService;
		return this;
	}

	public WashMachine setUsedBy(int usedBy) {
		this.usedBy = usedBy;
		return this;
	}

	public WashMachine setLocation(String location) {
		this.location = location;
		return this;
	}

	public WashMachine setWaitedBy(int waitedBy) {
		this.waitedBy = waitedBy;
		return this;
	}

	public WashMachine setStartsAt(String startsAt) {
		this.startsAt = startsAt;
		return this;
	}

	public WashMachine setWaitingCapacity(int waitingCapacity) {
		this.waitingCapacity = waitingCapacity;
		return this;
	}
	
	// Builder
	public static class WashMachineBuilder{
		// Fields
		private long id;
		private int status;
		private float pricePerService;
		private int usedBy;
		private String location;
		private int waitedBy;
		private String startsAt;
		private int waitingCapacity;
		
		public WashMachine build() {
			return new WashMachine(this);
		}
		
		// Builder's Setters
		public WashMachineBuilder setId(long id) {
			this.id = id;
			return this;
		}
		public WashMachineBuilder setStatus(int status) {
			this.status = status;
			return this;
		}
		public WashMachineBuilder setPricePerService(float pricePerService) {
			this.pricePerService = pricePerService;
			return this;
		}
		public WashMachineBuilder setUsedBy(int usedBy) {
			this.usedBy = usedBy;
			return this;
		}
		public WashMachineBuilder setLocation(String location) {
			this.location = location;
			return this;
		}
		public WashMachineBuilder setWaitedBy(int waitedBy) {
			this.waitedBy = waitedBy;
			return this;
		}
		public WashMachineBuilder setStartsAt(String startsAt) {
			this.startsAt = startsAt;
			return this;
		}
		public WashMachineBuilder setWaitingCapacity(int waitingCapacity) {
			this.waitingCapacity = waitingCapacity;
			return this;
		}
		
	}
}
