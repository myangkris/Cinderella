package com.cinderella.dao.machine;

import java.util.List;

import com.cinderella.entity.Machine;

public interface MachineDao {
    Machine getMachineById(int id);
    List<Machine> getWashMachineList(String address);
    void update(Machine machine);
    void deleteMachineById(int id);
}
