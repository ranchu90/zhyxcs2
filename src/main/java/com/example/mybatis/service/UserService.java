package com.example.mybatis.service;

import com.example.mybatis.domain.User;

import java.util.List;

/**
 * Created by fangyou on 2018/4/10.
 */
public interface UserService {
    User findById(Long id);
    List<User> getAllUsers();
}
