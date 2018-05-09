package com.zhyxcs.xxzz.mapper;

import com.zhyxcs.xxzz.domain.WorkIndex;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkIndexMapper {
    int deleteByPrimaryKey(String stransactionnum);

    int insert(WorkIndex record);

    int newWorkIndex(WorkIndex record);

    WorkIndex selectByPrimaryKey(String stransactionnum);

    List<WorkIndex> selectAll();

    int updateByPrimaryKey(WorkIndex record);

    int queryRecordTotalNum(@Param("userCode") String userCode, @Param("approvalState") String approvalState);

    List<WorkIndex> queryRecordByPageAndUserCode(@Param("pageSize") String pageSize, @Param("currentPage") String currentPage,
                                      @Param("userCode") String userCode, @Param("approvalState") String approvalState,
                                                 @Param("userLevel") String userLevel);

    int updateDepositorNameByPrimaryKey(WorkIndex workIndex);

    int updateApprovalStateNameByPrimaryKey(WorkIndex workIndex);
}
