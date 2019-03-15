package com.zhyxcs.xxzz.service;

import com.zhyxcs.xxzz.domain.Supervision;

import java.util.List;

public interface SupervisionService {
    int deleteByPrimaryKey(String stransactionnum);

    int insert(Supervision record);

    Supervision selectByPrimaryKey(String stransactionnum);

    List<Supervision> selectAll();

    int updateByPrimaryKey(Supervision record);

    int newSupervision(Supervision supervision);

    List<Supervision> queryRecordByPageAndUserCodeBankEntry(String pageSize,
                                                            String currentPage,
                                                            String userCode,
                                                            String approvalState,
                                                            String userLevel,
                                                            String bankCode,
                                                            String depositorName,
                                                            String businessType,
                                                            String kind);

    List<Supervision> queryRecordByPageAndUserCodeBankCharge(String pageSize,
                                                             String currentPage,
                                                             String userCode,
                                                             String approvalState,
                                                             String userLevel,
                                                             String bankCode,
                                                             String depositorName,
                                                             String businessType,
                                                             String kind);

    List<Supervision> queryRecordByPageAndUserCodeRenEntry(String pageSize,
                                                           String currentPage,
                                                           String userCode,
                                                           String approvalState,
                                                           String userLevel,
                                                           String pbcCode,
                                                           String bankCode,
                                                           String depositorName,
                                                           String businessType,
                                                           String kind);

    List<Supervision> queryRecordByPageAndUserCodeRenCharge(String pageSize,
                                                            String currentPage,
                                                            String userCode,
                                                            String approvalState,
                                                            String userLevel,
                                                            String pbcCode,
                                                            String bankCode,
                                                            String depositorName,
                                                            String businessType,
                                                            String kind);

    List<Supervision> queryRecordByPageAndUserCodeRenAdmin(String pageSize,
                                                           String currentPage,
                                                           String userCode,
                                                           String approvalState,
                                                           String userLevel,
                                                           String businessEmergency);

    int updateApprovalStateNameByPrimaryKey(Supervision supervision, String action);

    int queryRecordTotalNum(String useCode, String userLevel, String approvalState, String bankCode, String kind);

    int occupyTransaction(Supervision supervision);

    List<Supervision> queryRecordByConditions(String currentBankArea,
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
                                            String endTime,
                                            List<String> bankCodeList,
                                            List<String> pbcCodeList);
}
