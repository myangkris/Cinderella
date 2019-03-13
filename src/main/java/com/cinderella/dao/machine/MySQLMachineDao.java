package com.cinderella.dao.machine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cinderella.dao.mysql.MySQLDBUtil;
import com.cinderella.entity.Machine;
import com.cinderella.entity.Machine.MachineBuilder;

@Repository
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
                   int status = rs.getInt("status");
                   Double pricePerService = rs.getDouble("pricePerService");
                   int usedBy = rs.getInt("usedBy");
                   String locatedAt = rs.getString("locatedAt");
                   int waitedBy = rs.getInt("waitedBy");
                   Timestamp startsAt = rs.getTimestamp("startsAt");
                   int waitingCapacity = rs.getInt("waitingCapacity");
                   
                   MachineBuilder builder = new MachineBuilder();
                   builder.setId(id)
                          .setStatus(status)
                          .setPricePerService(pricePerService)
                          .setUsedBy(usedBy)
                          .setLocation(locatedAt)
                          .setWaitedBy(waitedBy)
                          .setStartsAt(startsAt)
                          .setWaitingCapacity(waitingCapacity);
                   
                   return builder.build();
                   
               }
           } catch (Exception e) {
               e.printStackTrace();
               return null;
           }
        return null;
    }

    @Override
    public List<Machine> getMachineListByLocation(String location) {
        List<Machine> machines = new ArrayList<>();
        String sql = "SELECT * FROM machines WHERE locatedAt = ?";
        
        try (Connection connection = DriverManager.getConnection(MySQLDBUtil.URL);
             PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, location);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MachineBuilder builder = new MachineBuilder();
                builder.setId(rs.getInt("id"));
                builder.setStatus(rs.getInt("status"));
                builder.setPricePerService(rs.getDouble("pricePerService"));
                builder.setUsedBy(rs.getInt("usedBy"));
                builder.setLocation(rs.getString("locatedAt"));
                builder.setWaitedBy(rs.getInt("WaitedBy"));
                builder.setStartsAt(rs.getTimestamp("startsAt"));
                builder.setWaitingCapacity(rs.getInt("waitingCapacity"));
                machines.add(builder.build());
            }
        } catch (SQLException e) {
            System.out.println("Not connected to MySQL");
            e.printStackTrace();
        }
        return machines;
    }

    @Override
    public void update(Machine machine) {
        String sql = "UPDATE machines SET "
                + "status = ?, "
                + "pricePerService = ?, " 
                + "usedBy = ?, " 
                + "locatedAt = ?, " 
                + "waitedBy = ?, "
                + "startsAt = ?, " 
                + "waitingCapacity = ? " 
                + "WHERE id = ? ";
        
        try (Connection connection = DriverManager.getConnection(MySQLDBUtil.URL);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            System.out.println("Update status to : " + machine.getStatus());
            ps.setInt(1, machine.getStatus());
            ps.setDouble(2, machine.getPricePerService());
            ps.setInt(3, machine.getUsedBy());
            if (machine.getLocation() != null) {
                ps.setString(4, machine.getLocation());
            } else {
                ps.setNull(4, java.sql.Types.VARCHAR);
            }
            ps.setInt(5, machine.getWaitedBy());
            ps.setTimestamp(6, machine.getStartsAt());
            ps.setInt(7, machine.getWaitingCapacity());
            System.out.println("Machine ID: " + machine.getId());
            ps.setInt(8, machine.getId());
            

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Not connected to MySQL");
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMachineById(int id) {
        String sql = "DELETE FROM machines WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(MySQLDBUtil.URL);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Not connected to MySQL");
            e.printStackTrace();
        }
    }
}
