package com.zhyxcs.xxzz.service;

import com.zhyxcs.xxzz.domain.WorkIndex;

import java.util.List;

public interface WorkIndexService {
    int newWorkIndex(WorkIndex record);

    List<WorkIndex> selectAll();

    int queryRecordTotalNum(String userCode, String approvalState);

    List<WorkIndex> queryRecordByPageAndUserCode(String pageSize,
                                                 String currentPage,
                                                 String userCode,
                                                 String approvalState,
                                                 String userLevel);

    int updateDepositorNameByPrimaryKey(WorkIndex record);

    int updateApprovalStateNameByPrimaryKey(WorkIndex workIndex, String action);

    int deleteByPrimaryKey(String stransactionnum);

    int updateWorkIndexByApprovalCodeAndIdentifier(WorkIndex workIndex);

    WorkIndex selectByPrimaryKey(String stransactionnum);
}
