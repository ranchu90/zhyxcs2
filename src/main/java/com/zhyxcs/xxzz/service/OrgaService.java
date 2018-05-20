package com.zhyxcs.xxzz.service;

import com.zhyxcs.xxzz.domain.Orga;

import java.util.List;

public interface OrgaService {
    Orga selectByPrimaryKey(String sbankcode);

    List<Orga> selectByBankKindAndPbcCode(
                                          String pbcCode,
                                          String bankTypeCode);
}
