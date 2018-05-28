package com.zhyxcs.xxzz.service;

import com.zhyxcs.xxzz.domain.BusinessStatistics;
import com.zhyxcs.xxzz.domain.GroundsForReturn;
import com.zhyxcs.xxzz.domain.Orga;
import com.zhyxcs.xxzz.domain.WorkIndex;
import com.zhyxcs.xxzz.utils.AuditStatus;
import com.zhyxcs.xxzz.utils.OvertimeStatus;

public interface BusinessStatisticsService {
    int insert(WorkIndex workIndex, AuditStatus auditStatus, OvertimeStatus overtimeStatus, GroundsForReturn groundsForReturn);
}
