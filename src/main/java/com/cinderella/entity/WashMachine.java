package com.cinderella.entity;

import java.sql.Timestamp;

public class WashMachine {
	// MySQL DB Schema
	/*  #   Column              Type            Null
		1	MachineIDPrimary	int(13)			No
		2	status	            int(255)		No
		3	pricePerService	    float(4,2)		Yes
		4	UsedById            int(7)			Yes
		5	locatedAtIndex	    varchar(255)	Yes
		6	WaitedByIndex	    int(7)			Yes
		7	startsAt	        timestamp		No
		8	waitingCapacity	    int(2)			Yes
	*/

	// Fields------------------------------------------------
	private long id;
	private int status;
	private float pricePerService;
	private Integer usedBy;
	private String location;
	private Integer waitedBy;
	private Timestamp startsAt;
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
	
	public Integer getUsedBy() {
		return usedBy;
	}
	
	public String getLocation() {
		return location;
	}
	
	public Integer getWaitedBy() {
		return waitedBy;
	}
	
	public Timestamp getStartsAt() {
		return startsAt;
	}
	
	public int getWaitingCapacity() {
		return waitingCapacity;
	}
	
	// Setters------------------------------------------------
	public WashMachine setId(long id) {
		this.id = id;
		return this;
	}
	
	public WashMachine setStatus(int status) {
		this.status = status;
		return this;
	}

	public WashMachine setPricePerService(float pricePerService) {
		this.pricePerService = pricePerService;
		return this;
	}

	public WashMachine setUsedBy(Integer usedBy) {
		this.usedBy = usedBy;
		return this;
	}

	public WashMachine setLocation(String location) {
		this.location = location;
		return this;
	}

	public WashMachine setWaitedBy(Integer waitedBy) {
		this.waitedBy = waitedBy;
		return this;
	}

	public WashMachine setStartsAt(Timestamp startsAt) {
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
		private Integer usedBy;
		private String location;
		private Integer waitedBy;
		private Timestamp startsAt;
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
		public WashMachineBuilder setUsedBy(Integer usedBy) {
			this.usedBy = usedBy;
			return this;
		}
		public WashMachineBuilder setLocation(String location) {
			this.location = location;
			return this;
		}
		public WashMachineBuilder setWaitedBy(Integer waitedBy) {
			this.waitedBy = waitedBy;
			return this;
		}
		public WashMachineBuilder setStartsAt(Timestamp startsAt) {
			this.startsAt = startsAt;
			return this;
		}
		public WashMachineBuilder setWaitingCapacity(int waitingCapacity) {
			this.waitingCapacity = waitingCapacity;
			return this;
		}
		
	}
}
