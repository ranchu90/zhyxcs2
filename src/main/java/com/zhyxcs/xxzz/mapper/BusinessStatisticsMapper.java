package com.zhyxcs.xxzz.mapper;

import com.zhyxcs.xxzz.domain.BusinessStatistics;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BusinessStatisticsMapper {
    int deleteByPrimaryKey(Long sid);

    int insert(BusinessStatistics record);

    BusinessStatistics selectByPrimaryKey(Long sid);

    List<BusinessStatistics> selectAll();

    int updateByPrimaryKey(BusinessStatistics record);

    int getCountByTransactionNum(@Param("transactionNum")String transactionNum);
}