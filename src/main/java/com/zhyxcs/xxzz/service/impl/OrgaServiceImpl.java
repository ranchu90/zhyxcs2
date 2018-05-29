package com.zhyxcs.xxzz.service.impl;

import com.zhyxcs.xxzz.domain.Orga;
import com.zhyxcs.xxzz.mapper.OrgaMapper;
import com.zhyxcs.xxzz.service.OrgaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrgaServiceImpl implements OrgaService {
    @Autowired
    private OrgaMapper orgaMapper;

    @Override
    public Orga selectByPrimaryKey(String sbankcode) {
        return orgaMapper.selectByPrimaryKey(sbankcode);
    }

    @Override
    public List<Orga> selectByBankTypeAndPbcCode(String pbcCode, String bankTypeCode) {
        return orgaMapper.selectByBankTypeAndPbcCode(pbcCode, bankTypeCode);
    }

    @Override
    public List<Orga> selectByBankKindAndPbcCode(String pbcCode, String bankKind) {
        return orgaMapper.selectByBankKindAndPbcCode(pbcCode, bankKind);
    }
}
