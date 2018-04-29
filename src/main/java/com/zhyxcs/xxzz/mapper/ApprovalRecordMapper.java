package com.zhyxcs.xxzz.mapper;

import com.zhyxcs.xxzz.domain.ApprovalRecord;
import java.util.List;

public interface ApprovalRecordMapper {
    int deleteByPrimaryKey(Long sid);

    int insert(ApprovalRecord record);

    ApprovalRecord selectByPrimaryKey(Long sid);

    List<ApprovalRecord> selectAll();

    int updateByPrimaryKey(ApprovalRecord record);
}