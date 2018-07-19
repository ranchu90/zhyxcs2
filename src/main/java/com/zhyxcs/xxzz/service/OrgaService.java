package com.zhyxcs.xxzz.service;

import com.zhyxcs.xxzz.domain.Orga;
import com.zhyxcs.xxzz.utils.OrgaLevelEnum;

import java.util.List;

public interface OrgaService {
    Orga selectByPrimaryKey(String sbankcode);

    List<Orga> selectByBankKindAndPbcCode(
            String pbcCode,
            String bankTypeCode);

    OrgaLevelEnum getOrgaLevel(String bankCode);

    List<String> getUnderBankcodeList(String bankCode);

    List<Orga> selectByBankTypeAndPbcCode(String pbcCode, String bankTypeCode);

    List<Orga> getByFullConditions(Orga orga);

    List<Orga> getPBCList();

    List<Orga> pageOrgaWithConditions(String bankAreaCode,
                                      String bankCityCode,
                                      String bankKind,
                                      String bankTypeCode,
                                      String topBankCode,
                                      String pbcode,
                                      String bankCode,
                                      String bankName,
                                      String bankState);

    int calculateNextOrgaNum(String bankCode);

    int deleteByBankCodeArray(String[] bankCodeArray);

    int insert(Orga orga);

    int updateByPrimaryKeyCheckPropertyIsNull(Orga orga);

    List<Orga> ifXian(String bankAreaCode, String bankKind);
}
