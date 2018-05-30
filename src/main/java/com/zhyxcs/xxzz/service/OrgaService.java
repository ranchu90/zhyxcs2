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
}
