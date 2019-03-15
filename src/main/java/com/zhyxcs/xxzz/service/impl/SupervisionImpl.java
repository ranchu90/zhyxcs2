package com.zhyxcs.xxzz.service.impl;

import com.zhyxcs.xxzz.domain.Supervision;
import com.zhyxcs.xxzz.mapper.SupervisionMapper;
import com.zhyxcs.xxzz.service.SupervisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupervisionImpl implements SupervisionService {
    @Autowired
    SupervisionMapper supervisionMapper;

    @Override
    public int deleteByPrimaryKey(String stransactionnum) {
        return supervisionMapper.deleteByPrimaryKey(stransactionnum);
    }

    @Override
    public int insert(Supervision record) {
        return supervisionMapper.insert(record);
    }

    @Override
    public Supervision selectByPrimaryKey(String stransactionnum) {
        return supervisionMapper.selectByPrimaryKey(stransactionnum);
    }

    @Override
    public List<Supervision> selectAll() {
        return supervisionMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Supervision record) {
        return supervisionMapper.updateByPrimaryKey(record);
    }

    @Override
    public int newSupervision(Supervision supervision) {
        return supervisionMapper.newSupervision(supervision);
    }

    @Override
    public List<Supervision> queryRecordByPageAndUserCodeBankEntry(String pageSize, String currentPage, String userCode,
                                                                   String approvalState, String userLevel, String bankCode,
                                                                   String depositorName, String businessType, String kind) {
        return supervisionMapper.queryRecordByPageAndUserCodeBankEntry(pageSize,
                currentPage,
                userCode,
                approvalState,
                userLevel,
                bankCode,
                depositorName,
                businessType,
                kind);
    }

    @Override
    public List<Supervision> queryRecordByPageAndUserCodeBankCharge(String pageSize, String currentPage,String userCode,
                                                                    String approvalState, String userLevel, String bankCode,
                                                                    String depositorName, String businessType, String kind) {
        return supervisionMapper.queryRecordByPageAndUserCodeBankCharge(pageSize, currentPage, userCode, approvalState,
                userLevel, bankCode, depositorName, businessType, kind);
    }

    @Override
    public List<Supervision> queryRecordByPageAndUserCodeRenEntry(String pageSize, String currentPage, String userCode, String approvalState,
                                                                  String userLevel, String pbcCode, String bankCode,
                                                                  String depositorName, String businessType, String kind) {
        return supervisionMapper.queryRecordByPageAndUserCodeRenEntry(pageSize, currentPage, userCode, approvalState,
                userLevel, pbcCode, bankCode, depositorName, businessType, kind);
    }

    @Override
    public List<Supervision> queryRecordByPageAndUserCodeRenCharge(String pageSize, String currentPage, String userCode, String approvalState,
                                                                   String userLevel, String pbcCode, String bankCode,
                                                                   String depositorName, String businessType,
                                                                   String kind) {
        return supervisionMapper.queryRecordByPageAndUserCodeRenCharge(pageSize, currentPage, userCode, approvalState,
                userLevel, pbcCode, bankCode, depositorName, businessType, kind);
    }

    @Override
    public List<Supervision> queryRecordByPageAndUserCodeRenAdmin(String pageSize, String currentPage, String userCode,
                                                                  String approvalState, String userLevel, String businessEmergency) {
        return null;
    }

    @Override
    public int updateApprovalStateNameByPrimaryKey(Supervision supervision, String action) {
        return supervisionMapper.updateApprovalStateNameByPrimaryKey(supervision, action);
    }

    @Override
    public int queryRecordTotalNum(String useCode, String userLevel, String approvalState, String bankCode, String kind) {
        return supervisionMapper.queryRecordTotalNum(useCode, userLevel, approvalState, bankCode, kind);
    }

    @Override
    public int occupyTransaction(Supervision supervision) {
        return supervisionMapper.occupyTransaction(supervision);
    }

    @Override
    public List<Supervision> queryRecordByConditions(String currentBankArea, String currentCity, String bankKind,
                                                     String bankType, String businessCategory, String accountType,
                                                     String orgaCode, String bankEntryUserCode, String bankReviewUserCode,
                                                     String renEntryUserCode, String renRecheckUserCode, String transactionNum,
                                                     String approvalCode, String identifier, String startTime, String endTime,
                                                     List<String> bankCodeList, List<String> pbcCodeList) {
        return supervisionMapper.queryRecordByConditions(currentBankArea, currentCity, bankKind, bankType,
                businessCategory, accountType, orgaCode, bankEntryUserCode, bankReviewUserCode, renEntryUserCode,
                renRecheckUserCode, transactionNum, approvalCode, identifier, startTime, endTime, bankCodeList, pbcCodeList);
    }
}
