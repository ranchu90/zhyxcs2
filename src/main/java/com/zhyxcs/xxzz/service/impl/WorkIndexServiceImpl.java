package com.zhyxcs.xxzz.service.impl;

import com.zhyxcs.xxzz.domain.WorkIndex;
import com.zhyxcs.xxzz.mapper.WorkIndexMapper;
import com.zhyxcs.xxzz.service.WorkIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkIndexServiceImpl implements WorkIndexService {
    @Autowired
    private WorkIndexMapper workIndexMapper;

    @Override
    public int newWorkIndex(WorkIndex record) {
        return workIndexMapper.newWorkIndex(record);
    }

    @Override
    public List<WorkIndex> selectAll() {
        return workIndexMapper.selectAll();
    }

    @Override
    public int queryRecordTotalNum(String userCode,
                                   String approvalState,
                                   String businessEmergency,
                                   String userLevel,
                                   String bankCode) {
        return workIndexMapper.queryRecordTotalNum(userCode, approvalState, businessEmergency, userLevel, bankCode);
    }

    @Override
    public List<WorkIndex> queryRecordByPageAndUserCodeBankEntry(String pageSize,
                                                                 String currentPage,
                                                                 String userCode,
                                                                 String approvalState,
                                                                 String userLevel,
                                                                 String businessEmergency,
                                                                 String bankCode) {
        return workIndexMapper.queryRecordByPageAndUserCodeBankEntry(pageSize, currentPage, userCode, approvalState,
                userLevel, businessEmergency, bankCode);
    }

    @Override
    public List<WorkIndex> queryRecordByPageAndUserCodeBankCharge(String pageSize,
                                                                  String currentPage,
                                                                  String userCode,
                                                                  String approvalState,
                                                                  String userLevel,
                                                                  String businessEmergency,
                                                                  String bankCode) {
        return workIndexMapper.queryRecordByPageAndUserCodeBankCharge(pageSize, currentPage, userCode, approvalState,
                userLevel, businessEmergency, bankCode);
    }

    @Override
    public List<WorkIndex> queryRecordByPageAndUserCodeRenEntry(String pageSize,
                                                                String currentPage,
                                                                String userCode,
                                                                String approvalState,
                                                                String userLevel,
                                                                String businessEmergency,
                                                                String pbcCode) {
        return workIndexMapper.queryRecordByPageAndUserCodeRenEntry(pageSize, currentPage, userCode, approvalState,
                userLevel, businessEmergency, pbcCode);
    }

    @Override
    public List<WorkIndex> queryRecordByPageAndUserCodeRenCharge(String pageSize,
                                                                 String currentPage,
                                                                 String userCode,
                                                                 String approvalState,
                                                                 String userLevel,
                                                                 String businessEmergency,
                                                                 String pbcCode) {
        return workIndexMapper.queryRecordByPageAndUserCodeRenCharge(pageSize, currentPage, userCode, approvalState,
                userLevel, businessEmergency, pbcCode);
    }

    @Override
    public List<WorkIndex> queryRecordByPageAndUserCodeRenAdmin(String pageSize,
                                                                String currentPage,
                                                                String userCode,
                                                                String approvalState,
                                                                String userLevel,
                                                                String businessEmergency) {
        return workIndexMapper.queryRecordByPageAndUserCodeRenAdmin(pageSize, currentPage, userCode, approvalState,
                userLevel, businessEmergency);
    }

    @Override
    public int updateDepositorNameByPrimaryKey(WorkIndex record) {
        return workIndexMapper.updateDepositorNameByPrimaryKey(record);
    }

    @Override
    public int updateApprovalStateNameByPrimaryKey(WorkIndex workIndex, String action) {
        return workIndexMapper.updateApprovalStateNameByPrimaryKey(workIndex, action);
    }

    @Override
    public int deleteByPrimaryKey(String stransactionnum) {
        return workIndexMapper.deleteByPrimaryKey(stransactionnum);
    }

    @Override
    public int updateWorkIndexByApprovalCodeAndIdentifier(WorkIndex workIndex) {
        return workIndexMapper.updateWorkIndexByApprovalCodeAndIdentifier(workIndex);
    }

    @Override
    public WorkIndex selectByPrimaryKey(String stransactionnum) {
        return workIndexMapper.selectByPrimaryKey(stransactionnum);
    }

    @Override
    public int updateWorkIndexBusinessEmergency(WorkIndex workIndex) {
        return workIndexMapper.updateWorkIndexBusinessEmergency(workIndex);
    }

}
