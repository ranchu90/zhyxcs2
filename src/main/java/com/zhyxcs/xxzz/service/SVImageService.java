package com.zhyxcs.xxzz.service;

import com.zhyxcs.xxzz.domain.Image;
import com.zhyxcs.xxzz.domain.SupervisionImage;

import java.util.List;

public interface SVImageService {
    int deleteByPrimaryKey(Long sid);

    int insert(SupervisionImage record);

    List<SupervisionImage> selectImagesByTranID(String sTransactionNum);

    SupervisionImage selectByPrimaryKey(Long sid);
}
