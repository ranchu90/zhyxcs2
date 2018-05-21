package com.zhyxcs.xxzz.mapper;

import com.zhyxcs.xxzz.domain.SystemLog;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SystemLogMapper {
    int deleteByPrimaryKey(Long sid);

    int insert(SystemLog record);

    SystemLog selectByPrimaryKey(Long sid);

    List<SystemLog> selectAll();

    int updateByPrimaryKey(SystemLog record);



    List<SystemLog> querySystemLogByPageWithConditions(@Param("userCode") String userCode,
                                                       @Param("userName") String userName,
                                                       @Param("bankCode") String bankCode,
                                                       @Param("bankName") String bankName,
                                                       @Param("ipAddress") String ipAddress,
                                                       @Param("comments") String comments,
                                                       @Param("startTime") Date startTime,
                                                       @Param("endTime") Date endTime);
}