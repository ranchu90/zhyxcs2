package com.zhyxcs.xxzz.mapper;

import com.zhyxcs.xxzz.domain.WorkIndex;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkIndexMapper {
    int deleteByPrimaryKey(String stransactionnum);

    int insert(WorkIndex record);

    WorkIndex selectByPrimaryKey(String stransactionnum);

    List<WorkIndex> selectAll();

    int updateByPrimaryKey(WorkIndex record);

    int newWorkIndex(WorkIndex record);

    int queryRecordTotalNum(@Param("userCode") String userCode,
                            @Param("approvalState") String approvalState,
                            @Param("businessEmergency") String businessEmergency,
                            @Param("userLevel") String userLevel);

    List<WorkIndex> queryRecordByPageAndUserCodeBankEntry(@Param("pageSize") String pageSize,
                                                 @Param("currentPage") String currentPage,
                                                 @Param("userCode") String userCode,
                                                 @Param("approvalState") String approvalState,
                                                 @Param("userLevel") String userLevel,
                                                 @Param("businessEmergency") String businessEmergency);

    List<WorkIndex> queryRecordByPageAndUserCodeBankCharge(@Param("pageSize") String pageSize,
                                                          @Param("currentPage") String currentPage,
                                                          @Param("userCode") String userCode,
                                                          @Param("approvalState") String approvalState,
                                                          @Param("userLevel") String userLevel,
                                                          @Param("businessEmergency") String businessEmergency);

    List<WorkIndex> queryRecordByPageAndUserCodeRenEntry(@Param("pageSize") String pageSize,
                                                           @Param("currentPage") String currentPage,
                                                           @Param("userCode") String userCode,
                                                           @Param("approvalState") String approvalState,
                                                           @Param("userLevel") String userLevel,
                                                           @Param("businessEmergency") String businessEmergency);

    List<WorkIndex> queryRecordByPageAndUserCodeRenCharge(@Param("pageSize") String pageSize,
                                                           @Param("currentPage") String currentPage,
                                                           @Param("userCode") String userCode,
                                                           @Param("approvalState") String approvalState,
                                                           @Param("userLevel") String userLevel,
                                                           @Param("businessEmergency") String businessEmergency);

    List<WorkIndex> queryRecordByPageAndUserCodeRenAdmin(@Param("pageSize") String pageSize,
                                                           @Param("currentPage") String currentPage,
                                                           @Param("userCode") String userCode,
                                                           @Param("approvalState") String approvalState,
                                                           @Param("userLevel") String userLevel,
                                                           @Param("businessEmergency") String businessEmergency);

    int updateDepositorNameByPrimaryKey(WorkIndex record);

    int updateApprovalStateNameByPrimaryKey(@Param("workIndex") WorkIndex workIndex, @Param("action") String action);

    int updateWorkIndexByApprovalCodeAndIdentifier(WorkIndex workIndex);

    int updateWorkIndexBusinessEmergency(WorkIndex workIndex);

}
