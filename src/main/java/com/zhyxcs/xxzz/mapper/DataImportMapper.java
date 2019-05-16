package com.zhyxcs.xxzz.mapper;

import com.zhyxcs.xxzz.domain.DataImport;
import java.util.List;

public interface DataImportMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DataImport record);

    DataImport selectByPrimaryKey(Integer id);

    List<DataImport> selectAll();

    int updateByPrimaryKey(DataImport record);
}