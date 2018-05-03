package com.zhyxcs.xxzz.service.impl;

import com.zhyxcs.xxzz.domain.User;
import com.zhyxcs.xxzz.mapper.UserMapper;
import com.zhyxcs.xxzz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectByPrimaryKey(String susercode) {
        return userMapper.selectByPrimaryKey(susercode);
    }
}
