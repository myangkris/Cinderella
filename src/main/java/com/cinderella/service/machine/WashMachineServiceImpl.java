package com.cinderella.service.machine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinderella.dao.mysql.MySQLConnection;
import com.cinderella.dao.mysql.MySQLDBUtil;
import com.cinderella.dto.WashingInfo;
import com.cinderella.entity.WashMachine;

@Service
public class WashMachineServiceImpl implements WashMachineService {
    private static final String DEFAULT_ADDRESS = "1234 Center Dr";
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

    @Override
    public List<WashMachine> listAllMachines() {
        return connection.getWashMachineList(DEFAULT_ADDRESS);
    }
    
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver").getConstructor().newInstance();
        String sql = "INSERT INTO washmachine(MachineID, status, pricePerService, UsedBy, locatedAt, startsAt, waitingCapacity) "
                + "VALUES(?, '3', '2.1', '0', '1234 Center Dr', ?, '1')";
        Calendar calendar = Calendar.getInstance();
        java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());
        try (Connection conn = DriverManager.getConnection(MySQLDBUtil.URL);
             PreparedStatement preparedStatement = conn.prepareStatement(sql);) {
            for (int i = 0; i < 15; i++) {
                preparedStatement.setLong(1, (long) (i * 1000 + i * i * 7));
                preparedStatement.setTimestamp(2, ourJavaTimestampObject);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        System.out.println("Import done successfully");
        
        System.out.println(new WashMachineServiceImpl().listAllMachines());
    }
}
