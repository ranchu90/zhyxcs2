package com.zhyxcs.xxzz.mapper;

import com.zhyxcs.xxzz.domain.ImageStandard;
import java.util.List;

public interface ImageStandardMapper {
    int deleteByPrimaryKey(Integer sid);

    int insert(ImageStandard record);

    ImageStandard selectByPrimaryKey(Integer sid);

    List<ImageStandard> selectAll();

    int updateByPrimaryKey(ImageStandard record);

    List<String> businessCatagory();

    List<String> accountType();
}