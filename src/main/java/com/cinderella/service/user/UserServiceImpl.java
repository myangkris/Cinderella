package com.cinderella.service.user;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinderella.dao.user.UserDao;
import com.cinderella.entity.User;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    
    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public void deleteUserById(int id) {
        userDao.deleteUserById(id);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public Double updateBalanceById(int id, double amount) {
        User user = userDao.getUserById(id);
        user.setBalance(user.getBalance() + amount);
        System.out.println(user.getUsername() + ": " + user.getBalance());
        userDao.update(user);
        return user.getBalance();
    }

    @Override
    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    @Override
    public JSONObject getUserByUsernameInJSON(String username) {
        return toJSON(userDao.getUserByUsername(username));
    }

    private JSONObject toJSON(User user) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("userId", user.getId());
            obj.put("name", user.getUsername());
            obj.put("balance", user.getBalance());
            obj.put("phoneNumber", user.getPhoneNumber());
            obj.put("bonusPoints", user.getBonusPoints());
            obj.put("email", user.getEmail());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

    
}
