package com.zhyxcs.xxzz.mapper;

import com.zhyxcs.xxzz.domain.SupervisionBusinessStatistics;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SupervisionBusinessStatisticsMapper {
    int deleteByPrimaryKey(Long sid);

    int insert(SupervisionBusinessStatistics record);

    SupervisionBusinessStatistics selectByPrimaryKey(Long sid);

    List<SupervisionBusinessStatistics> selectAll();

    int updateByPrimaryKey(SupervisionBusinessStatistics record);

    int getCountByTransactionNum(@Param("transactionNum") String transactionNum);
}