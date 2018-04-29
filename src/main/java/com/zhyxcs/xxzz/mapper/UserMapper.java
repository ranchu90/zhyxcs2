package com.zhyxcs.xxzz.mapper;

import com.zhyxcs.xxzz.domain.User;
import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(String susercode);

    int insert(User record);

    User selectByPrimaryKey(String susercode);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
}