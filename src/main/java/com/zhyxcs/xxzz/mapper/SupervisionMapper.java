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
                                                            @Param("businessType") String businessType);

    List<Supervision> queryRecordByPageAndUserCodeBankCharge(@Param("pageSize") String pageSize,
                                                            @Param("currentPage") String currentPage,
                                                            @Param("userCode") String userCode,
                                                            @Param("approvalState") String approvalState,
                                                            @Param("userLevel") String userLevel,
                                                            @Param("bankCode") String bankCode,
                                                            @Param("depositorName") String depositorName,
                                                            @Param("businessType") String businessType);

    int updateApprovalStateNameByPrimaryKey(Supervision supervision, @Param("action") String action);

    int queryRecordTotalNum(@Param("userCode") String userCode, @Param("userLevel") String userLevel, @Param("approvalState") String approvalState,
                            @Param("bankCode") String bankCode);

    int occupyTransaction(Supervision supervision);
}