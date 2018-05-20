package com.zhyxcs.xxzz.service;

import com.zhyxcs.xxzz.domain.WorkIndex;

import java.util.List;

public interface WorkIndexService {
    List<WorkIndex> selectAll();

    int deleteByPrimaryKey(String stransactionnum);

    int newWorkIndex(WorkIndex record);

    int queryRecordTotalNum(String userCode,
                            String approvalState,
                            String businessEmergency,
                            String userLevel);

    List<WorkIndex> queryRecordByPageAndUserCodeBankEntry(String pageSize,
                                                          String currentPage,
                                                          String userCode,
                                                          String approvalState,
                                                          String userLevel,
                                                          String businessEmergency);

    List<WorkIndex> queryRecordByPageAndUserCodeBankCharge(String pageSize,
                                                           String currentPage,
                                                           String userCode,
                                                           String approvalState,
                                                           String userLevel,
                                                           String businessEmergency);

    List<WorkIndex> queryRecordByPageAndUserCodeRenEntry(String pageSize,
                                                         String currentPage,
                                                         String userCode,
                                                         String approvalState,
                                                         String userLevel,
                                                         String businessEmergency);

    List<WorkIndex> queryRecordByPageAndUserCodeRenCharge(String pageSize,
                                                          String currentPage,
                                                          String userCode,
                                                          String approvalState,
                                                          String userLevel,
                                                          String businessEmergency);

    List<WorkIndex> queryRecordByPageAndUserCodeRenAdmin(String pageSize,
                                                         String currentPage,
                                                         String userCode,
                                                         String approvalState,
                                                         String userLevel,
                                                         String businessEmergency);

    int updateDepositorNameByPrimaryKey(WorkIndex record);

    int updateApprovalStateNameByPrimaryKey(WorkIndex workIndex, String action);


    int updateWorkIndexByApprovalCodeAndIdentifier(WorkIndex workIndex);

    WorkIndex selectByPrimaryKey(String stransactionnum);

    int updateWorkIndexBusinessEmergency(WorkIndex workIndex);
}
