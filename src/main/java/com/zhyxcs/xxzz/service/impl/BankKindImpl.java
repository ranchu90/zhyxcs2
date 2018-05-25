package com.zhyxcs.xxzz.service.impl;

import com.zhyxcs.xxzz.domain.BankKind;
import com.zhyxcs.xxzz.mapper.BankKindMapper;
import com.zhyxcs.xxzz.service.BankKindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BankKindImpl implements BankKindService {
    @Autowired
    private BankKindMapper bankKindMapper;

    @Override
    public List<BankKind> selectAll() {
        return bankKindMapper.selectAll();
    }
}
