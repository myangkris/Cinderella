package com.cinderella.service.machine;

import java.util.List;

import org.json.JSONArray;

import com.cinderella.dto.WashingInfo;
import com.cinderella.entity.Machine;

public interface WashMachineService {
    void updateWashMachineStatus(WashingInfo washingInfo, int status);
    List<Machine> listAllMachines();
    JSONArray listAllMachinesInJSONArray();
}
