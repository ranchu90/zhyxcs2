package com.zhyxcs.xxzz.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhyxcs.xxzz.domain.SystemLog;
import com.zhyxcs.xxzz.mapper.SystemLogMapper;
import com.zhyxcs.xxzz.service.SystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SystemLogServiceImpl implements SystemLogService {
    @Autowired
    private SystemLogMapper systemLogMapper;

    @Override
    public int insert(SystemLog record) {
        return systemLogMapper.insert(record);
    }


    @Override
    public List<SystemLog> querySystemLogByPageWithConditions(String userCode, String userName, String bankCode, String bankName, String ipAddress, String comments, Date startTime, Date endTime) {
        return systemLogMapper.querySystemLogByPageWithConditions(userCode, userName, bankCode, bankName, ipAddress, comments, startTime, endTime);
    }

}
