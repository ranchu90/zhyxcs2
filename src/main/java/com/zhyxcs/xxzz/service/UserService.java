package com.zhyxcs.xxzz.service;

import com.zhyxcs.xxzz.domain.User;

import java.util.HashMap;
import java.util.List;

public interface UserService {
    User selectByPrimaryKey(String susercode);

    int updateByPrimaryKey(User record);

    List<HashMap> selectBysAddUserCode(String addUserCode);

    List<HashMap> selectBysAddUserCodeAndBankType(String addUserCode,
                                                  String bankTypeCode);

    int insert(User record);

    int updateBasicByPrimaryKey(User record);

    List<HashMap> ifBankEntryHasBankReview( String addUserCode);

    int calculateUsersByBankCodeArray(String[] bankCodeArray);
}
