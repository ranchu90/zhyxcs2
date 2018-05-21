package com.zhyxcs.xxzz.service;

import com.zhyxcs.xxzz.domain.SystemLog;

import java.util.Date;
import java.util.List;

public interface SystemLogService {
    int insert(SystemLog record);

    List<SystemLog> querySystemLogByPageWithConditions(String userCode, String userName, String bankCode, String bankName, String ipAddress, String comments, Date startTime, Date endTime);
}
