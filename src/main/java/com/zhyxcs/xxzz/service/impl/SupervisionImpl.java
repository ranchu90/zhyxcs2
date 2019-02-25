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
    public List<Supervision> queryRecordByPageAndUserCodeBankEntry(String pageSize, String currentPage, String userCode, String approvalState, String userLevel, String bankCode, String depositorName, String businessType) {
        return supervisionMapper.queryRecordByPageAndUserCodeBankEntry(pageSize,
                userCode,
                currentPage,
                approvalState,
                userLevel,
                bankCode,
                depositorName,
                businessType);
    }

    @Override
    public List<Supervision> queryRecordByPageAndUserCodeBankCharge(String pageSize, String currentPage, String approvalState, String userLevel, String bankCode, String depositorName, String businessType) {
        return null;
    }

    @Override
    public List<Supervision> queryRecordByPageAndUserCodeRenEntry(String pageSize, String currentPage, String approvalState, String userLevel, String pbcCode, String bankCode, String depositorName, String businessType) {
        return null;
    }

    @Override
    public List<Supervision> queryRecordByPageAndUserCodeRenCharge(String pageSize, String currentPage, String approvalState, String userLevel, String pbcCode, String bankCode, String depositorName, String businessType) {
        return null;
    }

    @Override
    public List<Supervision> queryRecordByPageAndUserCodeRenAdmin(String pageSize, String currentPage, String userCode, String approvalState, String userLevel, String businessEmergency) {
        return null;
    }
}
