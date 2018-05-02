package com.zhyxcs.xxzz.service;

import com.zhyxcs.xxzz.domain.User;

public interface UserService {
    User selectByPrimaryKey(String susercode);
}
