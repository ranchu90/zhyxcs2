package com.zhyxcs.xxzz.mapper;

import com.zhyxcs.xxzz.domain.Image;
import java.util.List;

public interface ImageMapper {
    int deleteByPrimaryKey(Long sid);

    int insert(Image record);

    Image selectByPrimaryKey(Long sid);

    List<Image> selectAll();

    int updateByPrimaryKey(Image record);

    List<Image> selectImagesByTranID(String sTransactionNum);
}
