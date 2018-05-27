package com.zhyxcs.xxzz.mapper;

import com.zhyxcs.xxzz.domain.TBusinessStatistics;
import java.util.List;

public interface TBusinessStatisticsMapper {
    int deleteByPrimaryKey(Long sid);

    int insert(TBusinessStatistics record);

    TBusinessStatistics selectByPrimaryKey(Long sid);

    List<TBusinessStatistics> selectAll();

    int updateByPrimaryKey(TBusinessStatistics record);
}