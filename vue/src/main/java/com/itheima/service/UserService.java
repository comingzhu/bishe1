package com.itheima.service;

import com.itheima.pojo.User;

public interface UserService {
    
    User findByUsername(String username);
    
    User findById(Integer id);
    
    void register(User user);
    
    User login(String username, String password);
}
