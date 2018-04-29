package com.zhyxcs.xxzz.service;

import com.zhyxcs.xxzz.domain.Image;

public interface ImageService {
    int deleteByPrimaryKey(Long sid);
    int insert(Image record);
}
