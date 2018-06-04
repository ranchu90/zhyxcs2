package com.zhyxcs.xxzz.mapper;

import com.zhyxcs.xxzz.domain.BankKind;
import com.zhyxcs.xxzz.domain.BankType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BankTypeMapper {
    int deleteByPrimaryKey(String sbanktypecode);

    int insert(BankType record);

    BankType selectByPrimaryKey(String sbanktypecode);

    List<BankType> selectAll();

    int updateByPrimaryKey(BankType record);


    List<BankType> selectAllBusinessBank();

    List<BankType> getTypesByBankKind(@Param("bankKindCode")String bankKindCode);
}
