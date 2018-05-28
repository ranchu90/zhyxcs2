package com.zhyxcs.xxzz.service.impl;

import com.zhyxcs.xxzz.domain.BusinessStatistics;
import com.zhyxcs.xxzz.mapper.BusinessStatisticsMapper;
import com.zhyxcs.xxzz.service.BusinessStatisticsService;
import org.springframework.stereotype.Service;

@Service
public class BusinessStatisticsServiceImpl implements BusinessStatisticsService {
    private BusinessStatisticsMapper businessStatisticsMapper;
    @Override
    public int insert(BusinessStatistics record) {
        return businessStatisticsMapper.insert(record);
    }
}
