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
                                   String businessEmergency) {
        return workIndexMapper.queryRecordTotalNum(userCode, approvalState, businessEmergency);
    }

    @Override
    public List<WorkIndex> queryRecordByPageAndUserCode(String pageSize,
                                                        String currentPage,
                                                        String userCode,
                                                        String approvalState,
                                                        String userLevel,
                                                        String businessEmergency) {
        return workIndexMapper.queryRecordByPageAndUserCode(pageSize, currentPage, userCode, approvalState, userLevel, businessEmergency);
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
}
