package com.zhyxcs.xxzz.mapper;

import com.zhyxcs.xxzz.domain.BankType;
import java.util.List;

public interface BankTypeMapper {
    int deleteByPrimaryKey(String sbanktypecode);

    int insert(BankType record);

    BankType selectByPrimaryKey(String sbanktypecode);

    List<BankType> selectAll();

    int updateByPrimaryKey(BankType record);
}