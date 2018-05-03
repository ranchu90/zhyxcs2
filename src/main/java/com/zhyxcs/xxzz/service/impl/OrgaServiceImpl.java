package com.zhyxcs.xxzz.service.impl;

import com.zhyxcs.xxzz.domain.Orga;
import com.zhyxcs.xxzz.mapper.OrgaMapper;
import com.zhyxcs.xxzz.service.OrgaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrgaServiceImpl implements OrgaService {
    @Autowired
    private OrgaMapper orgaMapper;

    @Override
    public Orga selectByPrimaryKey(String sbankcode) {
        return orgaMapper.selectByPrimaryKey(sbankcode);
    }
}
