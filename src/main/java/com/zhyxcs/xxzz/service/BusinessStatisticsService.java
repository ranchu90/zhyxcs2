package com.zhyxcs.xxzz.service;

import com.zhyxcs.xxzz.domain.GroundsForReturn;
import com.zhyxcs.xxzz.domain.WorkIndex;
import com.zhyxcs.xxzz.utils.AuditStatus;
import com.zhyxcs.xxzz.utils.OvertimeStatus;

import java.util.Date;
import java.util.List;

public interface BusinessStatisticsService {
    int insert(WorkIndex workIndex, AuditStatus auditStatus, OvertimeStatus overtimeStatus, GroundsForReturn groundsForReturn);

    int measureBusiness(String pbcCode,
                        String areaCode,
                        String cityCode,
                        String bankKind,
                        String bankType,
                        String bankCode,
                        Date startTime,
                        Date endTime,
                        String businessCategory,
                        String accountType,
                        String passed,
                        List<String> pbcCodeList,
                        List<String> commerceCodeList);

    int mistakeBusiness(String pbcCode,
                        String areaCode,
                        String cityCode,
                        String bankKind,
                        String bankType,
                        String bankCode,
                        Date startTime,
                        Date endTime,
                        String businessCategory,
                        String grounds,
                        List<String> pbcCodeList,
                        List<String> commerceCodeList);
}
