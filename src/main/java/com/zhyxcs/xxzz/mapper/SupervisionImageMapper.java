package com.zhyxcs.xxzz.mapper;

import com.zhyxcs.xxzz.domain.SupervisionImage;
import java.util.List;

public interface SupervisionImageMapper {
    int deleteByPrimaryKey(Long sid);

    int insert(SupervisionImage record);

    SupervisionImage selectByPrimaryKey(Long sid);

    List<SupervisionImage> selectAll();

    int updateByPrimaryKey(SupervisionImage record);

    List<SupervisionImage> selectImagesByTranID(String sTransactionNum);
}