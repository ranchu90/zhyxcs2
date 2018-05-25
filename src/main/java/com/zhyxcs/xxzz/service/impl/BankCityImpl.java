package com.zhyxcs.xxzz.service.impl;

import com.zhyxcs.xxzz.domain.BankCity;
import com.zhyxcs.xxzz.mapper.BankCityMapper;
import com.zhyxcs.xxzz.service.BankCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankCityImpl implements BankCityService {
    @Autowired
    private BankCityMapper bankCityMapper;

    @Override
    public List<BankCity> selectAll() {
        return bankCityMapper.selectAll();
    }

    @Override
    public BankCity selectByPrimaryKey(String sbankcitycode) {
        return bankCityMapper.selectByPrimaryKey(sbankcitycode);
    }

    @Override
    public List<BankCity> selectByArea(String bankAreaCode) {
        return bankCityMapper.selectByArea(bankAreaCode);
    }
}
