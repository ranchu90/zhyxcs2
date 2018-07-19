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

    List<Orga> selectByBankKindAndPbcCode(@Param("pbcCode") String pbcCode,
                                          @Param("bankKind") String bankKind);

    List<Orga> selectByBankTypeAndPbcCode(@Param("pbcCode") String pbcCode,
                                          @Param("bankTypeCode") String bankTypeCode);

    String getOrgaLevel(@Param("bankCode") String bankCode);

    List<String> getUnderBankcodeList(@Param("bankCode") String bankCode);

    List<Orga> getByFullConditions(@Param("orga") Orga orga);

    List<Orga> getPBCList();

    List<Orga> pageOrgaWithConditions(@Param("bankAreaCode") String bankAreaCode,
                                      @Param("bankCityCode") String bankCityCode,
                                      @Param("bankKind") String bankKind,
                                      @Param("bankTypeCode") String bankTypeCode,
                                      @Param("topBankCode") String topBankCode,
                                      @Param("pbcode") String pbcode,
                                      @Param("bankCode") String bankCode,
                                      @Param("bankName") String bankName,
                                      @Param("bankState") String bankState);

    int calculateNextOrgaNum(@Param("bankCode") String bankCode,
                             @Param("bankKindChar") String bankKindChar);

    int deleteByBankCodeArray(@Param("bankCodeArray") String[] bankCodeArray);

    int updateByPrimaryKeyCheckPropertyIsNull(Orga record);

    List<Orga> ifXian(@Param("bankCode") String bankCode,
                      @Param("bankKind") String bankKind);
}
