package com.zhyxcs.xxzz.service.impl;

import com.zhyxcs.xxzz.domain.LicenceImage;
import com.zhyxcs.xxzz.mapper.LicenceImageMapper;
import com.zhyxcs.xxzz.service.LicenceImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LicenceImageImpl implements LicenceImageService {

    @Autowired
    private LicenceImageMapper licenceImageMapper;

    @Override
    public int insert(LicenceImage record) {
        return licenceImageMapper.insert(record);
    }

    @Override
    public int deleteByPrimaryKey(String sTransactionNum) {
        return licenceImageMapper.deleteByPrimaryKey(sTransactionNum);
    }

    @Override
    public LicenceImage selectByPrimaryKey(String transactionNum) {
        return licenceImageMapper.selectByPrimaryKey(transactionNum);
    }
}
