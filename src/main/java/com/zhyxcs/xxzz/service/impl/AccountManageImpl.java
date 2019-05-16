package com.zhyxcs.xxzz.service.impl;

import com.zhyxcs.xxzz.domain.AccountManageSys;
import com.zhyxcs.xxzz.mapper.AccountManageSysMapper;
import com.zhyxcs.xxzz.service.AccountManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountManageImpl implements AccountManageService {
    @Autowired
    private AccountManageSysMapper accountManageSysMapper;
    @Override
    public int insert(AccountManageSys record) {
        return accountManageSysMapper.insert(record);
    }

    @Override
    public List<AccountManageSys> selectByUnitCodeAccountNumAndOpenDate(String unitCode, String accountNum, String openDate) {
        return accountManageSysMapper.selectByUnitCodeAccountNumAndOpenDate(unitCode, accountNum, openDate);
    }

    @Override
    public int updateByUnitCodeAccountNumAndOpenDate(AccountManageSys record) {
        return accountManageSysMapper.updateByUnitCodeAccountNumAndOpenDate(record);
    }

    @Override
    public List<AccountManageSys> selectByUnitCodeAccountNumAndOpenDateVersionTwo(String status, String accountNum) {
        return accountManageSysMapper.selectByUnitCodeAccountNumAndOpenDateVersionTwo(status, accountNum);
    }

    @Override
    public List<AccountManageSys> selectByUnitCodeAccountNumAndOpenDateBlur(String depositorName, String unitCode) {
        return accountManageSysMapper.selectByUnitCodeAccountNumAndOpenDateBlur(depositorName, unitCode);
    }
}
