package com.zhyxcs.xxzz.service;

import com.zhyxcs.xxzz.domain.WorkIndex;

import java.util.Date;
import java.util.List;

public interface WorkIndexService {
    List<WorkIndex> selectAll();

    int deleteByPrimaryKey(String stransactionnum);

    int newWorkIndex(WorkIndex record);

    int queryRecordTotalNum(String userCode,
                            String approvalState,
                            String businessEmergency,
                            String userLevel,
                            String bankCode,
                            String ifUploadLicense,
                            String ifRecheck);

    List<WorkIndex> queryRecordByPageAndUserCodeBankEntry(String pageSize,
                                                          String currentPage,
                                                          String userCode,
                                                          String approvalState,
                                                          String userLevel,
                                                          String businessEmergency,
                                                          String bankCode);

    List<WorkIndex> queryRecordByPageAndUserCodeBankCharge(String pageSize,
                                                           String currentPage,
                                                           String userCode,
                                                           String approvalState,
                                                           String userLevel,
                                                           String businessEmergency,
                                                           String bankCode);

    List<WorkIndex> queryRecordByPageAndUserCodeRenEntry(String pageSize,
                                                         String currentPage,
                                                         String userCode,
                                                         String approvalState,
                                                         String userLevel,
                                                         String businessEmergency,
                                                         String pbcCode,
                                                         String ifUploadLicense,
                                                         String ifRecheck);

    List<WorkIndex> queryRecordByPageAndUserCodeRenCharge(String pageSize,
                                                          String currentPage,
                                                          String userCode,
                                                          String approvalState,
                                                          String userLevel,
                                                          String businessEmergency,
                                                          String pbcCode,
                                                          String ifUploadLicense,
                                                          String ifRecheck);

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

    List<WorkIndex> queryRecordByConditions(String currentBankArea,
                                            String currentCity,
                                            String bankKind,
                                            String bankType,
                                            String businessCategory,
                                            String accountType,
                                            String orgaCode,
                                            String bankEntryUserCode,
                                            String bankReviewUserCode,
                                            String renEntryUserCode,
                                            String renRecheckUserCode,
                                            String transactionNum,
                                            String approvalCode,
                                            String identifier,
                                            String startTime,
                                            String endTime);

    int calculateWorksByBankCode(String bankCode);

    List<WorkIndex> queryDiary(String bankOrPBC,
                               String currentUserBankCode,
                               String bankKind,
                               String bankType,
                               String bankName,
                               Date startTime,
                               Date endTime);

    int occupyTransaction(WorkIndex workIndex);
}
