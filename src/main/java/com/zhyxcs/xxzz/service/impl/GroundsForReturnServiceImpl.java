package com.zhyxcs.xxzz.service.impl;

import com.zhyxcs.xxzz.domain.GroundsForReturn;
import com.zhyxcs.xxzz.mapper.GroundsForReturnMapper;
import com.zhyxcs.xxzz.service.GroundsForReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GroundsForReturnServiceImpl implements GroundsForReturnService {
    @Autowired
    private GroundsForReturnMapper groundsForReturnMapper;
    @Override
    public List<GroundsForReturn> selectAll() {
        return groundsForReturnMapper.selectAll();
    }
}
