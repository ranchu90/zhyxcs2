package com.zhyxcs.xxzz.service.impl;

import com.zhyxcs.xxzz.domain.ApprovalRecord;
import com.zhyxcs.xxzz.mapper.ApprovalRecordMapper;
import com.zhyxcs.xxzz.service.ApprovalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApprovalRecordImpl implements ApprovalRecordService {
    @Autowired
    private ApprovalRecordMapper approvalRecordMapper;

    @Override
    public int insert(ApprovalRecord record) {
        return approvalRecordMapper.insert(record);
    }

    @Override
    public List<ApprovalRecord> selectByTranID(String transactioinNum) {
        return approvalRecordMapper.selectByTranID(transactioinNum);
    }

    @Override
    public ApprovalRecord selectByUserCodeAndOpinion(String transactionNum, String approvelUserCode, String approvelOpinion) {
        return approvalRecordMapper.selectByUserCodeAndOpinion(transactionNum,approvelUserCode, approvelOpinion);
    }
}
