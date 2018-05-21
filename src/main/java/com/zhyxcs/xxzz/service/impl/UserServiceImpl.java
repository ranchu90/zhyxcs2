package com.zhyxcs.xxzz.service.impl;

import com.zhyxcs.xxzz.domain.User;
import com.zhyxcs.xxzz.mapper.UserMapper;
import com.zhyxcs.xxzz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectByPrimaryKey(String susercode) {
        return userMapper.selectByPrimaryKey(susercode);
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<HashMap> selectBysAddUserCode(String addUserCode) {
        return userMapper.selectBysAddUserCode(addUserCode);
    }

    @Override
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Override
    public int updateBasicByPrimaryKey(User record) {
        return userMapper.updateBasicByPrimaryKey(record);
    }
}
