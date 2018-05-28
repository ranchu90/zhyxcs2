package com.zhyxcs.xxzz.mapper;

import com.zhyxcs.xxzz.domain.BusinessStatistics;
import java.util.List;

public interface BusinessStatisticsMapper {
    int deleteByPrimaryKey(Long sid);

    int insert(BusinessStatistics record);

    BusinessStatistics selectByPrimaryKey(Long sid);

    List<BusinessStatistics> selectAll();

    int updateByPrimaryKey(BusinessStatistics record);
}