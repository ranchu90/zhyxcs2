package com.zhyxcs.xxzz.service.impl;

import com.zhyxcs.xxzz.domain.Image;
import com.zhyxcs.xxzz.mapper.ImageMapper;
import com.zhyxcs.xxzz.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageMapper imageMapper;

    @Override
    public int deleteByPrimaryKey(Long sid) {
        return imageMapper.deleteByPrimaryKey(sid);
    }

    @Override
    public int insert(Image record) {
        return imageMapper.insert(record);
    }

    @Override
    public List<Image> selectImagesByTranID(String sTransactionNum) {
        return imageMapper.selectImagesByTranID(sTransactionNum);
    }
}
