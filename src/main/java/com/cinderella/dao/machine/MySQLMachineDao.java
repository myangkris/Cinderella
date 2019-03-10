package com.cinderella.dao.machine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.cinderella.dao.mysql.MySQLDBUtil;
import com.cinderella.entity.Machine;

public class MySQLMachineDao implements MachineDao {

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public Machine getMachineById(int id) {
        String sql = "select * from machines where id = ?";
        try (Connection connection = DriverManager.getConnection(MySQLDBUtil.URL);
             PreparedStatement ps = connection.prepareStatement(sql)){
               ps.setInt(1, id);
               ResultSet rs = ps.executeQuery();
               if (rs.next()) {
                   //int id = rs.getInt("id");
                   
               }
           } catch (Exception e) {
               e.printStackTrace();
           }
        return null;
    }

    @Override
    public List<Machine> getWashMachineList(String address) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void update(Machine machine) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteMachineById(int id) {
        // TODO Auto-generated method stub

    }

}
