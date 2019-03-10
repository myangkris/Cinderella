package com.cinderella.service.user;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.cinderella.entity.User;

@Service
public interface UserService {
    User getUserById(int id);
    User getUserByUsername(String username);
    void add(User user);
    void deleteUserById(int id);
    void update(User user);
    Double updateBalanceById(int id, double amount);
    JSONObject getUserByUsernameInJSON(String username);
}
