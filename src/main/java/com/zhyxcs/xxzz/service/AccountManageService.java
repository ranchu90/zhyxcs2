package com.zhyxcs.xxzz.service;

import com.zhyxcs.xxzz.domain.AccountManageSys;

import java.util.List;

public interface AccountManageService {
    int insert(AccountManageSys record);

    List<AccountManageSys> selectByUnitCodeAccountNumAndOpenDate(String unitCode, String accountNum, String openDate);

    //deprecated
    int updateByUnitCodeAccountNumAndOpenDate(AccountManageSys record);

    List<AccountManageSys> selectByUnitCodeAccountNumAndOpenDateVersionTwo(String status, String accountNum);

    List<AccountManageSys> selectByUnitCodeAccountNumAndOpenDateBlur(String depositorName, String unitCode);
}
