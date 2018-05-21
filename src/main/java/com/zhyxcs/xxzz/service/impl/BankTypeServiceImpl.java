package com.zhyxcs.xxzz.service.impl;

import com.zhyxcs.xxzz.domain.BankType;
import com.zhyxcs.xxzz.mapper.BankTypeMapper;
import com.zhyxcs.xxzz.service.BankTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankTypeServiceImpl implements BankTypeService {
    @Autowired
    private BankTypeMapper bankTypeMapper;

    @Override
    public List<BankType> selectAllBusinessBank() {
        return bankTypeMapper.selectAllBusinessBank();
    }
}
