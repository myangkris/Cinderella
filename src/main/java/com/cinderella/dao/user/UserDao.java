package com.cinderella.dao.user;

import com.cinderella.entity.User;

public interface UserDao {
    void add(User user);
    void deleteUserById(int id);
    void update(User user);
    User getUserById(int id);
    User getUserByUsername(String username);
}
