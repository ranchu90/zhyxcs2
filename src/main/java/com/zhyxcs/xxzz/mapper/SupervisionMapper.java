package com.zhyxcs.xxzz.mapper;

import com.zhyxcs.xxzz.domain.Supervision;
import java.util.List;

public interface SupervisionMapper {
    int deleteByPrimaryKey(String stransactionnum);

    int insert(Supervision record);

    Supervision selectByPrimaryKey(String stransactionnum);

    List<Supervision> selectAll();

    int updateByPrimaryKey(Supervision record);
}