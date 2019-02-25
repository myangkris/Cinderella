package com.cinderella.service.machine;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinderella.dao.mysql.MySQLConnection;
import com.cinderella.dto.WashingInfo;
import com.cinderella.entity.WashMachine;

@Service
public class WashMachineServiceImpl implements WashMachineService {
    @Autowired
    private MySQLConnection connection;
    
    @Override
    public void updateWashMachineStatus(WashingInfo washingInfo, int status) {
        WashMachine washMachine = connection.findWashMachineById(Long.parseLong(washingInfo.getMachineId()));
        washMachine.setStatus(status);
        washMachine.setUsedBy(Integer.parseInt(washingInfo.getUserId()));
        if (WashMachine.STATUS_WASHING == status) {
            washMachine.setStartsAt(Timestamp.valueOf(LocalDateTime.now(ZoneOffset.of("-16"))));
        }
        connection.updateWashMachine(washMachine);
    }
}
