package com.zhyxcs.xxzz.service;

import com.zhyxcs.xxzz.domain.Image;

import java.util.List;

public interface ImageService {
    int deleteByPrimaryKey(Long sid);

    int insert(Image record);

    List<Image> selectImagesByTranID(String sTransactionNum);
}
