package com.zhyxcs.xxzz.mapper;

import com.zhyxcs.xxzz.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(String susercode);

    int insert(User record);

    User selectByPrimaryKey(String susercode);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    int updateBasicByPrimaryKey(User record);

    List<HashMap> selectBysAddUserCode(@Param("addUserCode") String addUserCode,
                                       @Param("bankTypeCode") String bankTypeCode,
                                       @Param("bankCode") String bankCode,
                                       @Param("bankName") String bankName,
                                       @Param("userName") String userName,
                                       @Param("userCode") String userCode);

    List<HashMap> ifBankEntryHasBankReview(@Param("addUserCode") String addUserCode);

    int calculateUsersByBankCodeArray(@Param("bankCodeArray")String[] bankCodeArray);
}
