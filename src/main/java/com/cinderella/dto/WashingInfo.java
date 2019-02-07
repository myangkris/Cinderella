package com.cinderella.dto;

public class WashingInfo {
    private String userId;
    private String machineId;
    private long washingDuration;
    
    public WashingInfo (WashingInfoBuilder builder) {
        this.userId = builder.userId;
        this.machineId = builder.machineId;
        this.washingDuration = builder.washingDuration;
    }
    
    public String getUserId() {
        return userId;
    }

    public String getMachineId() {
        return machineId;
    }

    public long getWashingDuration() {
        return washingDuration;
    }

    public static class WashingInfoBuilder {
        private String userId;
        private String machineId;
        private long washingDuration;
        
        public WashingInfoBuilder withUserId(String userId) {
            this.userId = userId;
            return this;
        }
        
        public WashingInfoBuilder withMachineId(String machineId) {
            this.machineId = machineId;
            return this;
        }
        
        public WashingInfoBuilder withWashingDuration(long washingDuration) {
            this.washingDuration = washingDuration;
            return this;
        }
        
        public WashingInfo build() {
            return new WashingInfo(this);
        }
    }
}
