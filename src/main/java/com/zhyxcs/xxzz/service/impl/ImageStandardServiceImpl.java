package com.zhyxcs.xxzz.service.impl;

import com.zhyxcs.xxzz.domain.ImageStandard;
import com.zhyxcs.xxzz.mapper.ImageStandardMapper;
import com.zhyxcs.xxzz.service.ImageStandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ImageStandardServiceImpl implements ImageStandardService {
    @Autowired
    private ImageStandardMapper imageStandardMapper;
    
    @Override
    public List<String> businessCatagory() {
        return imageStandardMapper.businessCatagory();
    }

    @Override
    public List<String> accountType() {
        return imageStandardMapper.accountType();
    }

}
