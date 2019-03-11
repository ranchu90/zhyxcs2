package com.zhyxcs.xxzz.mapper;

import com.zhyxcs.xxzz.domain.Supervision;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SupervisionMapper {
    int deleteByPrimaryKey(String stransactionnum);

    int insert(Supervision record);

    Supervision selectByPrimaryKey(String stransactionnum);

    List<Supervision> selectAll();

    int updateByPrimaryKey(Supervision record);

    int newSupervision(Supervision supervision);

    List<Supervision> queryRecordByPageAndUserCodeBankEntry(@Param("pageSize") String pageSize,
                                                            @Param("currentPage") String currentPage,
                                                            @Param("userCode") String userCode,
                                                            @Param("approvalState") String approvalState,
                                                            @Param("userLevel") String userLevel,
                                                            @Param("bankCode") String bankCode,
                                                            @Param("depositorName") String depositorName,
                                                            @Param("businessType") String businessType,
                                                            @Param("kind") String kind);

    List<Supervision> queryRecordByPageAndUserCodeBankCharge(@Param("pageSize") String pageSize,
                                                            @Param("currentPage") String currentPage,
                                                            @Param("userCode") String userCode,
                                                            @Param("approvalState") String approvalState,
                                                            @Param("userLevel") String userLevel,
                                                            @Param("bankCode") String bankCode,
                                                            @Param("depositorName") String depositorName,
                                                            @Param("businessType") String businessType,
                                                            @Param("kind") String kind);

    List<Supervision> queryRecordByPageAndUserCodeRenEntry(@Param("pageSize") String pageSize,
                                                           @Param("currentPage") String currentPage,
                                                           @Param("userCode") String userCode,
                                                           @Param("approvalState") String approvalState,
                                                           @Param("userLevel") String userLevel,
                                                           @Param("pbcCode") String pbcCode,
                                                           @Param("bankCode") String bankCode,
                                                           @Param("depositorName") String depositorName,
                                                           @Param("businessType") String businessType,
                                                           @Param("kind") String kind);

    int updateApprovalStateNameByPrimaryKey(Supervision supervision, @Param("action") String action);

    int queryRecordTotalNum(@Param("userCode") String userCode, @Param("userLevel") String userLevel, @Param("approvalState") String approvalState,
                            @Param("bankCode") String bankCode, @Param("kind") String kind);

    int occupyTransaction(Supervision supervision);
}