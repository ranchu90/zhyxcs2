package com.zhyxcs.xxzz.service.impl;

import com.zhyxcs.xxzz.domain.SupervisionGroundsForReturn;
import com.zhyxcs.xxzz.mapper.SupervisionGroundsForReturnMapper;
import com.zhyxcs.xxzz.service.SVGroundsForReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SVGroundsForReturnImpl implements SVGroundsForReturnService {
    @Autowired
    SupervisionGroundsForReturnMapper supervisionGroundsForReturnMapper;

    @Override
    public List<SupervisionGroundsForReturn> selectAll() {
        return supervisionGroundsForReturnMapper.selectAll();
    }
}
