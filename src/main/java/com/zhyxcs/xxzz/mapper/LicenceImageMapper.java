package com.zhyxcs.xxzz.mapper;

import com.zhyxcs.xxzz.domain.LicenceImage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LicenceImageMapper {
    int insert(LicenceImage record);

    List<LicenceImage> selectAll();

    int deleteByPrimaryKey(@Param("transactionNum") String transactionNum);

    LicenceImage selectByPrimaryKey(@Param("transactionNum") String transactionNum);

    int updateByPrimaryKey(LicenceImage image);
}
