package com.zhyxcs.xxzz.mapper;

import com.zhyxcs.xxzz.domain.SupervisionImageStandard;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SupervisionImageStandardMapper {
    int deleteByPrimaryKey(Integer sid);

    int insert(SupervisionImageStandard record);

    SupervisionImageStandard selectByPrimaryKey(Integer sid);

    List<SupervisionImageStandard> selectAll();

    int updateByPrimaryKey(SupervisionImageStandard record);

    List svBusinessCategory();

    List<String> svAccountType(@Param("businessCategory") String businessCategory);
}