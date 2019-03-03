package com.cinderella.service.timing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinderella.dao.timing.WashingMessageProducer;
import com.cinderella.dto.WashingInfo;
import com.cinderella.entity.WashMachine;
import com.cinderella.service.account.UserAccount;
import com.cinderella.service.machine.WashMachineService;

@Service
public class WashingServiceImpl implements WashingService {

    @Autowired
    private WashingMessageProducer washingMessageProducer;
    
    @Autowired
    private WashMachineService washMachineService;
    
    
    
    @Override
    public void process(WashingInfo washingInfo) {
        washingMessageProducer.produce(washingInfo);
        washMachineService.updateWashMachineStatus(washingInfo, WashMachine.STATUS_WASHING);
        UserAccount userAccout = new UserAccount(washingInfo.getUsername());
        userAccout.pay();
    }
}
