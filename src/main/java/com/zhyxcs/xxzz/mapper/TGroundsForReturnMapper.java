package com.zhyxcs.xxzz.mapper;

import com.zhyxcs.xxzz.domain.TGroundsForReturn;
import java.util.List;

public interface TGroundsForReturnMapper {
    int deleteByPrimaryKey(Long sid);

    int insert(TGroundsForReturn record);

    TGroundsForReturn selectByPrimaryKey(Long sid);

    List<TGroundsForReturn> selectAll();

    int updateByPrimaryKey(TGroundsForReturn record);
}