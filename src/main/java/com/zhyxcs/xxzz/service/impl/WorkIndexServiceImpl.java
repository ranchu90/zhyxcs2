package com.zhyxcs.xxzz.service.impl;

import com.zhyxcs.xxzz.domain.WorkIndex;
import com.zhyxcs.xxzz.mapper.WorkIndexMapper;
import com.zhyxcs.xxzz.service.WorkIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkIndexServiceImpl implements WorkIndexService {
    @Autowired
    private WorkIndexMapper workIndexMapper;
    
    @Override 
    public int newWorkIndex(WorkIndex record) {
        return workIndexMapper.newWorkIndex(record);
    }
}
