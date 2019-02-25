package com.cinderella.service.machine;

import com.cinderella.dto.WashingInfo;

public interface WashMachineService {
    void updateWashMachineStatus(WashingInfo washingInfo, int status);
}
