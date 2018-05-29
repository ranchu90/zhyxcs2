package com.zhyxcs.xxzz.mapper;

import com.zhyxcs.xxzz.domain.Orga;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrgaMapper {
    int deleteByPrimaryKey(String sbankcode);

    int insert(Orga record);

    Orga selectByPrimaryKey(String sbankcode);

    List<Orga> selectAll();

    int updateByPrimaryKey(Orga record);

    List<Orga> selectByBankTypeAndPbcCode(
                                          @Param("pbcCode") String pbcCode,
                                          @Param("bankTypeCode") String bankTypeCode);

    List<Orga> selectByBankKindAndPbcCode(
            @Param("pbcCode") String pbcCode,
            @Param("bankKind") String bankKind);
}
