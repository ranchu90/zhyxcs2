package com.zhyxcs.xxzz.mapper;

import com.zhyxcs.xxzz.domain.ApprovalRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ApprovalRecordMapper {
    int deleteByPrimaryKey(Long sid);

    int insert(ApprovalRecord record);

    ApprovalRecord selectByPrimaryKey(Long sid);

    ApprovalRecord selectByUserCodeAndOpinion(@Param("transactionNum") String transactionNum,
                                              @Param("approvelUserCode") String approvelUserCode,
                                              @Param("approvelOpinion") String approvelOpinion);

    List<ApprovalRecord> selectAll();

    int updateByPrimaryKey(ApprovalRecord record);

    List<ApprovalRecord> selectByTranID(@Param("transactioinNum") String transactioinNum);
}
