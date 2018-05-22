package com.zhyxcs.xxzz.service;

import com.zhyxcs.xxzz.domain.BankKind;
import com.zhyxcs.xxzz.domain.BankType;

import java.util.List;

public interface BankTypeService {
    List<BankType> selectAllBusinessBank();

    List<BankType> selectAll();
}
