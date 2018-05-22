package com.zhyxcs.xxzz.mapper;

import com.zhyxcs.xxzz.domain.ImageStandard;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ImageStandardMapper {
    int deleteByPrimaryKey(Integer sid);

    int insert(ImageStandard record);

    ImageStandard selectByPrimaryKey(Integer sid);

    List<ImageStandard> selectAll();

    int updateByPrimaryKey(ImageStandard record);

    List<String> businessCategory();

    List<String> accountType(@Param("businessCategory") String businessCategory);

    List<String> certificateType(@Param("businessCategory") String businessCategory, @Param("accountType") String accountType);
}
