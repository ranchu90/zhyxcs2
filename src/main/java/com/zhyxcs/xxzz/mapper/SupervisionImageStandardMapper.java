package com.zhyxcs.xxzz.mapper;

import com.zhyxcs.xxzz.domain.SupervisionImageStandard;
import java.util.List;

public interface SupervisionImageStandardMapper {
    int deleteByPrimaryKey(Integer sid);

    int insert(SupervisionImageStandard record);

    SupervisionImageStandard selectByPrimaryKey(Integer sid);

    List<SupervisionImageStandard> selectAll();

    int updateByPrimaryKey(SupervisionImageStandard record);
}