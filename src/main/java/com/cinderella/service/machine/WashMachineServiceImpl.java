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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinderella.dao.machine.MachineDao;
import com.cinderella.dao.mysql.MySQLConnection;
import com.cinderella.dao.mysql.MySQLDBUtil;
import com.cinderella.dto.WashingInfo;
import com.cinderella.entity.Machine;

@Service
public class WashMachineServiceImpl implements WashMachineService {
    private static final String DEFAULT_ADDRESS = "1234 Center Dr";
    @Autowired
    private MySQLConnection connection;
    
    @Override
    public void updateWashMachineStatus(WashingInfo washingInfo, int status) {
        Machine washMachine = connection.findWashMachineById(Long.parseLong(washingInfo.getMachineId()));
        washMachine.setStatus(status);
        washMachine.setUsedBy(Integer.parseInt(washingInfo.getUserId()));
        if (Machine.STATUS_WASHING == status) {
            washMachine.setStartsAt(Timestamp.valueOf(LocalDateTime.now(ZoneOffset.of("-16"))));
        }
        connection.updateWashMachine(washMachine);
    }
    /*@Autowired
    private MachineDao machineDao;
    
    @Override
    public void updateWashMachineStatus(WashingInfo washingInfo, int status) {
        Machine washMachine = machineDao.findWashMachineById(Long.parseLong(washingInfo.getMachineId()));
        washMachine.setStatus(status);
        washMachine.setUsedBy(Integer.parseInt(washingInfo.getUserId()));
        if (Machine.STATUS_WASHING == status) {
            washMachine.setStartsAt(Timestamp.valueOf(LocalDateTime.now(ZoneOffset.of("-16"))));
        }
        connection.updateWashMachine(washMachine);
    }*/

    @Override
    public List<Machine> listAllMachines() {
        return connection.getWashMachineList(DEFAULT_ADDRESS);
    }
    
    @Override
    public JSONArray listAllMachinesInJSONArray() {
        List<Machine> list = listAllMachines();
        JSONArray array = new JSONArray();
        for(Machine machine : list) {
            JSONObject obj = new JSONObject();
            Double price = machine.getPricePerService();
//`MachineID`, `status`, `pricePerService`, `UsedBy`, `locatedAt`, `WaitedBy`, `startsAt`, `waitingCapacity`
            try {
                obj.put("MachineID", machine.getId());
                obj.put("status", machine.getStatus());
                obj.put("pricePerService", price.toString());
                obj.put("UsedBy", machine.getUsedBy());
                obj.put("locatedAt", machine.getLocation());
                obj.put("WaitedBy", machine.getWaitedBy());
                obj.put("startsAt", machine.getStartsAt());
                obj.put("waitingCapacity", machine.getWaitingCapacity());
                array.put(obj);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return array;
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
