package com.zhyxcs.xxzz.service.impl;

import com.zhyxcs.xxzz.domain.SystemLog;
import com.zhyxcs.xxzz.mapper.SystemLogMapper;
import com.zhyxcs.xxzz.service.SystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SystemLogServiceImpl implements SystemLogService {
    @Autowired
    private SystemLogMapper systemLogMapper;

    @Override
    public int insert(SystemLog record) {
        return systemLogMapper.insert(record);
    }

    @Override
    public int queryCountWithConditions(String userCode, String userName, String bankCode, String bankName, String ipAddress, String comments, Date startTime, Date endTime) {
        return systemLogMapper.queryCountWithConditions(userCode, userName, bankCode, bankName, ipAddress, comments, startTime, endTime);
    }

    @Override
    public List<SystemLog> querySystemLogByPageWithConditions(String pageSize, String currentPage, String userCode, String userName, String bankCode, String bankName, String ipAddress, String comments, Date startTime, Date endTime) {
        return systemLogMapper.querySystemLogByPageWithConditions(pageSize, currentPage, userCode, userName, bankCode, bankName, ipAddress, comments, startTime, endTime);
    }

}
