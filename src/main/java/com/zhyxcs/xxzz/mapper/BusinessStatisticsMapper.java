package com.zhyxcs.xxzz.mapper;

import com.zhyxcs.xxzz.domain.BusinessStatistics;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface BusinessStatisticsMapper {
    int deleteByPrimaryKey(Long sid);

    int insert(BusinessStatistics record);

    BusinessStatistics selectByPrimaryKey(Long sid);

    List<BusinessStatistics> selectAll();

    int updateByPrimaryKey(BusinessStatistics record);

    int getCountByTransactionNum(@Param("transactionNum") String transactionNum);

    int measureBusiness(@Param("pbcCode") String pbcCode,
                        @Param("areaCode") String areaCode,
                        @Param("cityCode") String cityCode,
                        @Param("bankKind") String bankKind,
                        @Param("bankType") String bankType,
                        @Param("bankCode") String bankCode,
                        @Param("startTime") Date startTime,
                        @Param("endTime") Date endTime,
                        @Param("businessCategory") String businessCategory,
                        @Param("accountType") String accountType,
                        @Param("passed") String passed,
                        @Param("pbcCodeList") List<String> pbcCodeList,
                        @Param("commerceCodeList") List<String> commerceCodeList);

    int mistakeBusiness(@Param("pbcCode") String pbcCode,
                        @Param("areaCode") String areaCode,
                        @Param("cityCode") String cityCode,
                        @Param("bankKind") String bankKind,
                        @Param("bankType") String bankType,
                        @Param("bankCode") String bankCode,
                        @Param("startTime") Date startTime,
                        @Param("endTime") Date endTime,
                        @Param("businessCategory") String businessCategory,
                        @Param("grounds") String grounds,
                        @Param("pbcCodeList") List<String> pbcCodeList,
                        @Param("commerceCodeList") List<String> commerceCodeList);
}