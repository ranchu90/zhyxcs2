package com.zhyxcs.xxzz.service;

import com.zhyxcs.xxzz.domain.BankCity;

import java.util.List;

public interface BankCityService {
    List<BankCity> selectAll();

    BankCity selectByPrimaryKey(String sbankcitycode);

    List<BankCity> selectByArea(String bankAreaCode);
}
