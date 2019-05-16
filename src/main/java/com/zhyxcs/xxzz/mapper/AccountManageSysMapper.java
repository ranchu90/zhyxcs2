package com.zhyxcs.xxzz.mapper;

import com.zhyxcs.xxzz.domain.AccountManageSys;
import java.util.List;

public interface AccountManageSysMapper {
    int insert(AccountManageSys record);

    List<AccountManageSys> selectAll();

    List<AccountManageSys> selectByUnitCodeAccountNumAndOpenDate(String unitCode, String accountNum, String openDate);

    List<AccountManageSys> selectByUnitCodeAccountNumAndOpenDateVersionTwo(String status, String accountNum);

    List<AccountManageSys> selectByUnitCodeAccountNumAndOpenDateBlur(String depositorName, String unitCode);

    int updateByUnitCodeAccountNumAndOpenDate(AccountManageSys record);
}