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
    public int queryRecordTotalNum(String userCode, String approvalState) {
        return workIndexMapper.queryRecordTotalNum(userCode, approvalState);
    }

    @Override
    public List<WorkIndex> queryRecordByPageAndUserCode(String pageSize, String currentPage, String userCode,
                                                        String approvalState, String userLevel) {
        return workIndexMapper.queryRecordByPageAndUserCode(pageSize, currentPage, userCode, approvalState, userLevel);
    }

    @Override
    public int updateDepositorNameByPrimaryKey(WorkIndex record) {
        return workIndexMapper.updateDepositorNameByPrimaryKey(record);
    }

    @Override
    public int updateApprovalStateNameByPrimaryKey(WorkIndex record) {
        return workIndexMapper.updateApprovalStateNameByPrimaryKey(record);
    }

    @Override
    public int deleteByPrimaryKey(String stransactionnum) {
        return workIndexMapper.deleteByPrimaryKey(stransactionnum);
    }
}
