package com.example.mybatis.dao;

import com.example.mybatis.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by fangyou on 2018/4/10.
 */
public interface UserDao {
    User findById(@Param("id") Long id);
    List<User> getAllUsers();
}
