package com.zhyxcs.xxzz.service.impl;

import com.zhyxcs.xxzz.domain.BankArea;
import com.zhyxcs.xxzz.mapper.BankAreaMapper;
import com.zhyxcs.xxzz.service.BankAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankAreaImpl implements BankAreaService {
    @Autowired
    private BankAreaMapper bankAreaMapper;

    @Override
    public List<BankArea> selectAll() {
        return bankAreaMapper.selectAll();
    }
}
