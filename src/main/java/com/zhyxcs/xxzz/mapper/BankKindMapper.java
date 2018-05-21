package com.zhyxcs.xxzz.mapper;

import com.zhyxcs.xxzz.domain.BankKind;
import java.util.List;

public interface BankKindMapper {
    int deleteByPrimaryKey(String sbankkind);

    int insert(BankKind record);

    BankKind selectByPrimaryKey(String sbankkind);

    List<BankKind> selectAll();

    int updateByPrimaryKey(BankKind record);
}
