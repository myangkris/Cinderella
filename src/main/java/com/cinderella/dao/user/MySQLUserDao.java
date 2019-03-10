package com.cinderella.dao.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.cinderella.dao.mysql.MySQLDBUtil;
import com.cinderella.entity.User;
import com.cinderella.entity.User.UserBuilder;

@Repository
public class MySQLUserDao implements UserDao {
    
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(User user) {
        String sql = "INSERT INTO users (username, password, balance, phoneNumber, bonusPoints, email) "
                     + "VALUES(?, ?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(MySQLDBUtil.URL);
             PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setDouble(3, user.getBalance());
            ps.setString(4, user.getPhoneNumber());
            ps.setInt(5, user.getBonusPoints());
            ps.setString(6, user.getEmail());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUserById(int id) {
        String sql = "delete from users where id = ?";
        try (Connection connection = DriverManager.getConnection(MySQLDBUtil.URL);
             PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE users SET "
                + "username = ?, "
                + "password = ?, " 
                + "balance = ?, " 
                + "phoneNumber = ?, " 
                + "bonusPoints = ?, "
                + "email = ? "
                + "where id = ?";
        try (Connection connection = DriverManager.getConnection(MySQLDBUtil.URL);
             PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setDouble(3, user.getBalance());
            ps.setString(4, user.getPhoneNumber());
            ps.setInt(5, user.getBonusPoints());
            ps.setString(6, user.getEmail());
            ps.setInt(7, user.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUserById(int id) {
        String sql = "select * from users where id = ?";
        try (Connection connection = DriverManager.getConnection(MySQLDBUtil.URL);
             PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                double balance = rs.getDouble("balance");
                String phoneNumber = rs.getString("phoneNumber");
                int bonusPoints = rs.getInt("bonusPoints");
                String email = rs.getString("email");
                
                UserBuilder builder = new UserBuilder();
                builder.setId(id)
                       .setUsername(username)
                       .setPassword(password)
                       .setBalance(balance)
                       .setPhoneNumber(phoneNumber)
                       .setBonusPoints(bonusPoints)
                       .setEmail(email);
                
                return builder.build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public User getUserByUsername(String username) {
        String sql = "select * from users where username = ?";
        try (Connection connection = DriverManager.getConnection(MySQLDBUtil.URL);
             PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String password = rs.getString("password");
                double balance = rs.getDouble("balance");
                String phoneNumber = rs.getString("phoneNumber");
                int bonusPoints = rs.getInt("bonusPoints");
                String email = rs.getString("email");
                
                UserBuilder builder = new UserBuilder();
                builder.setId(id)
                       .setUsername(username)
                       .setPassword(password)
                       .setBalance(balance)
                       .setPhoneNumber(phoneNumber)
                       .setBonusPoints(bonusPoints)
                       .setEmail(email);
                
                return builder.build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
