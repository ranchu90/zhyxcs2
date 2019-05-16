package com.zhyxcs.xxzz.service;

import com.zhyxcs.xxzz.domain.GroundsForReturn;
import com.zhyxcs.xxzz.domain.Supervision;
import com.zhyxcs.xxzz.utils.AuditStatus;
import com.zhyxcs.xxzz.utils.OvertimeStatus;

public interface SupervisionBusinessStaticsService {
    int insert(Supervision supervision, AuditStatus auditStatus, OvertimeStatus overtimeStatus, GroundsForReturn groundsForReturn);
}
