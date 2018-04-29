package com.zhyxcs.xxzz.mapper;

import com.zhyxcs.xxzz.domain.SystemLog;
import java.util.List;

public interface SystemLogMapper {
    int deleteByPrimaryKey(Long sid);

    int insert(SystemLog record);

    SystemLog selectByPrimaryKey(Long sid);

    List<SystemLog> selectAll();

    int updateByPrimaryKey(SystemLog record);
}