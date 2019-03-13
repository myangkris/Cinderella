package com.cinderella.entity;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Machine {
    public static final int STATUS_AVAILABLE = 0;
    public static final int STATUS_WASHING = 1;
    public static final int STATUS_ERROR = 2;
	// Fields------------------------------------------------
    @JsonProperty
	private final int id;
    @JsonProperty
	private int status;
    @JsonProperty
	private double pricePerService;
    @JsonProperty
	private int usedBy;
    @JsonProperty
	private String location;
    @JsonProperty
	private int waitedBy;
    @JsonProperty
	private Timestamp startsAt;
    @JsonProperty
	private int waitingCapacity;
	
	// Constructor------------------------------------------------
	private Machine(MachineBuilder builder) {
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
	public int getId() {
		return id;
	}
	public int getStatus() {
		return status;
	}
	
	public double getPricePerService() {
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
	
	public Timestamp getStartsAt() {
		return startsAt;
	}
	
	public int getWaitingCapacity() {
		return waitingCapacity;
	}
	
	// Setters------------------------------------------------	
	public Machine setStatus(int status) {
		this.status = status;
		return this;
	}

	public Machine setPricePerService(double pricePerService) {
		this.pricePerService = pricePerService;
		return this;
	}

	public Machine setUsedBy(int usedBy) {
		this.usedBy = usedBy;
		return this;
	}

	public Machine setLocation(String location) {
		this.location = location;
		return this;
	}

	public Machine setWaitedBy(int waitedBy) {
		this.waitedBy = waitedBy;
		return this;
	}

	public Machine setStartsAt(Timestamp startsAt) {
		this.startsAt = startsAt;
		return this;
	}

	public Machine setWaitingCapacity(int waitingCapacity) {
		this.waitingCapacity = waitingCapacity;
		return this;
	}
	
	// Builder
	public static class MachineBuilder{
		// Fields
		private int id;
		private int status;
		private double pricePerService;
		private int usedBy;
		private String location;
		private int waitedBy;
		private Timestamp startsAt;
		private int waitingCapacity;
		
		public Machine build() {
			return new Machine(this);
		}
		
		// Builder's Setters
		public MachineBuilder setId(int id) {
			this.id = id;
			return this;
		}
		public MachineBuilder setStatus(int status) {
			this.status = status;
			return this;
		}
		public MachineBuilder setPricePerService(double pricePerService) {
			this.pricePerService = pricePerService;
			return this;
		}
		public MachineBuilder setUsedBy(int usedBy) {
			this.usedBy = usedBy;
			return this;
		}
		public MachineBuilder setLocation(String location) {
			this.location = location;
			return this;
		}
		public MachineBuilder setWaitedBy(int waitedBy) {
			this.waitedBy = waitedBy;
			return this;
		}
		public MachineBuilder setStartsAt(Timestamp startsAt) {
			this.startsAt = startsAt;
			return this;
		}
		public MachineBuilder setWaitingCapacity(int waitingCapacity) {
			this.waitingCapacity = waitingCapacity;
			return this;
		}
		
	}
}
