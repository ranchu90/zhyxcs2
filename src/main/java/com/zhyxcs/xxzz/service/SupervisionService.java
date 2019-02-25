package com.zhyxcs.xxzz.service;

import com.zhyxcs.xxzz.domain.Supervision;

import java.util.List;

public interface SupervisionService {
    int deleteByPrimaryKey(String stransactionnum);

    int insert(Supervision record);

    Supervision selectByPrimaryKey(String stransactionnum);

    List<Supervision> selectAll();

    int updateByPrimaryKey(Supervision record);

    List<Supervision> queryRecordByPageAndUserCodeBankEntry(String pageSize,
                                                            String currentPage,
                                                            String userCode,
                                                            String approvalState,
                                                            String userLevel,
                                                            String bankCode,
                                                            String depositorName,
                                                            String businessType);

    List<Supervision> queryRecordByPageAndUserCodeBankCharge(String pageSize,
                                                             String currentPage,
                                                             String approvalState,
                                                             String userLevel,
                                                             String bankCode,
                                                             String depositorName,
                                                             String businessType);

    List<Supervision> queryRecordByPageAndUserCodeRenEntry(String pageSize,
                                                           String currentPage,
                                                           String approvalState,
                                                           String userLevel,
                                                           String pbcCode,
                                                           String bankCode,
                                                           String depositorName,
                                                           String businessType);

    List<Supervision> queryRecordByPageAndUserCodeRenCharge(String pageSize,
                                                            String currentPage,
                                                            String approvalState,
                                                            String userLevel,
                                                            String pbcCode,
                                                            String bankCode,
                                                            String depositorName,
                                                            String businessType);

    List<Supervision> queryRecordByPageAndUserCodeRenAdmin(String pageSize,
                                                           String currentPage,
                                                           String userCode,
                                                           String approvalState,
                                                           String userLevel,
                                                           String businessEmergency);
}
