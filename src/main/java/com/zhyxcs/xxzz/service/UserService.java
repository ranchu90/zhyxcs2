package com.zhyxcs.xxzz.service;

import com.zhyxcs.xxzz.domain.User;

import java.util.HashMap;
import java.util.List;

public interface UserService {
    User selectByPrimaryKey(String susercode);

    int updateByPrimaryKey(User record);

    List<HashMap> selectBysAddUserCode(String addUserCode,
                                       String bankTypeCode,
                                       String bankCode,
                                       String bankName,
                                       String userName,
                                       String userCode);

    int insert(User record);

    int updateBasicByPrimaryKey(User record);

    List<HashMap> ifBankEntryHasBankReview( String addUserCode);

    int calculateUsersByBankCodeArray(String[] bankCodeArray);
}
