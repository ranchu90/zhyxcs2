package com.zhyxcs.xxzz.mapper;

import com.zhyxcs.xxzz.domain.Orga;
import java.util.List;

public interface OrgaMapper {
    int deleteByPrimaryKey(String sbankcode);

    int insert(Orga record);

    Orga selectByPrimaryKey(String sbankcode);

    List<Orga> selectAll();

    int updateByPrimaryKey(Orga record);
}