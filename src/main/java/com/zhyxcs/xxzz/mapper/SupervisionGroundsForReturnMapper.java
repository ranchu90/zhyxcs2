package com.zhyxcs.xxzz.mapper;

import com.zhyxcs.xxzz.domain.SupervisionGroundsForReturn;
import java.util.List;

public interface SupervisionGroundsForReturnMapper {
    int deleteByPrimaryKey(Long sid);

    int insert(SupervisionGroundsForReturn record);

    SupervisionGroundsForReturn selectByPrimaryKey(Long sid);

    List<SupervisionGroundsForReturn> selectAll();

    int updateByPrimaryKey(SupervisionGroundsForReturn record);
}