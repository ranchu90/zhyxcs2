package com.zhyxcs.xxzz.mapper;

import com.zhyxcs.xxzz.domain.BankCity;
import java.util.List;

public interface BankCityMapper {
    int deleteByPrimaryKey(String sbankcitycode);

    int insert(BankCity record);

    BankCity selectByPrimaryKey(String sbankcitycode);

    List<BankCity> selectAll();

    List<BankCity> selectByArea(String bankAreaCode);

    int updateByPrimaryKey(BankCity record);
}
