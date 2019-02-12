package com.zhyxcs.xxzz.service;

import com.zhyxcs.xxzz.domain.LicenceImage;

public interface LicenceImageService {
    int insert(LicenceImage record);

    int deleteByPrimaryKey(String transactionNum);

    LicenceImage selectByPrimaryKey(String transactionNum);

    int updateByPrimaryKey(LicenceImage image);
}
