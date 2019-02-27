package com.zhyxcs.xxzz.service.impl;

import com.zhyxcs.xxzz.domain.Image;
import com.zhyxcs.xxzz.domain.SupervisionImage;
import com.zhyxcs.xxzz.mapper.SupervisionImageMapper;
import com.zhyxcs.xxzz.service.SVImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SVImageImpl implements SVImageService {
    @Autowired
    SupervisionImageMapper supervisionImageMapper;

    @Override
    public int deleteByPrimaryKey(Long sid) {
        return supervisionImageMapper.deleteByPrimaryKey(sid);
    }

    @Override
    public int insert(SupervisionImage record) {
        return supervisionImageMapper.insert(record);
    }

    @Override
    public List<SupervisionImage> selectImagesByTranID(String sTransactionNum) {
        return null;
    }

    @Override
    public SupervisionImage selectByPrimaryKey(Long sid) {
        return supervisionImageMapper.selectByPrimaryKey(sid);
    }
}
