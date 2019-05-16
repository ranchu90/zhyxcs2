package com.zhyxcs.xxzz.service.impl;

import com.zhyxcs.xxzz.domain.DataImport;
import com.zhyxcs.xxzz.mapper.DataImportMapper;
import com.zhyxcs.xxzz.service.DataImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataImportImpl implements DataImportService {
    @Autowired
    private DataImportMapper dataImportMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return dataImportMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(DataImport record) {
        return dataImportMapper.insert(record);
    }

    @Override
    public DataImport selectByPrimaryKey(Integer id) {
        return dataImportMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<DataImport> selectAll() {
        return dataImportMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(DataImport record) {
        return dataImportMapper.updateByPrimaryKey(record);
    }
}
