package com.zhyxcs.xxzz.mapper;

import com.zhyxcs.xxzz.domain.WorkIndex;
import java.util.List;

public interface WorkIndexMapper {
    int deleteByPrimaryKey(String stransactionnum);

    int insert(WorkIndex record);
    
    int newWorkIndex(WorkIndex record);

    WorkIndex selectByPrimaryKey(String stransactionnum);

    List<WorkIndex> selectAll();

    int updateByPrimaryKey(WorkIndex record);
}