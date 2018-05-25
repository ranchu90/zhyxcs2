package com.zhyxcs.xxzz.service;

import com.zhyxcs.xxzz.domain.ApprovalRecord;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ApprovalRecordService {
    int insert(ApprovalRecord record);

    List<ApprovalRecord> selectByTranID( String transactioinNum);

    ApprovalRecord selectByUserCodeAndOpinion(String transactionNum,
                                                    String approvelUserCode,
                                                    String approvelOpinion);
}
