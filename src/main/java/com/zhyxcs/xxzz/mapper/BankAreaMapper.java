package com.zhyxcs.xxzz.mapper;

import com.zhyxcs.xxzz.domain.BankArea;
import java.util.List;

public interface BankAreaMapper {
    int deleteByPrimaryKey(String sbankareacode);

    int insert(BankArea record);

    BankArea selectByPrimaryKey(String sbankareacode);

    List<BankArea> selectAll();

    int updateByPrimaryKey(BankArea record);
}