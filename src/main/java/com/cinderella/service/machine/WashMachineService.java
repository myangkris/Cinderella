package com.cinderella.service.machine;

import java.util.List;

import com.cinderella.dto.WashingInfo;
import com.cinderella.entity.WashMachine;

public interface WashMachineService {
    void updateWashMachineStatus(WashingInfo washingInfo, int status);
    List<WashMachine> listAllMachines();
}
