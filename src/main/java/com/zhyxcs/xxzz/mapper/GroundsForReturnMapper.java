package com.zhyxcs.xxzz.mapper;

import com.zhyxcs.xxzz.domain.GroundsForReturn;
import java.util.List;

public interface GroundsForReturnMapper {
    int deleteByPrimaryKey(Long sid);

    int insert(GroundsForReturn record);

    GroundsForReturn selectByPrimaryKey(Long sid);

    List<GroundsForReturn> selectAll();

    int updateByPrimaryKey(GroundsForReturn record);
}