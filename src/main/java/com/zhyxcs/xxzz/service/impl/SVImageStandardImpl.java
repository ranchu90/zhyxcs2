package com.zhyxcs.xxzz.service.impl;

import com.zhyxcs.xxzz.mapper.SupervisionImageStandardMapper;
import com.zhyxcs.xxzz.service.SVImageStandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class SVImageStandardImpl implements SVImageStandardService {
    @Autowired
    SupervisionImageStandardMapper svImageStandardMapper;

    @Override
    public List<String> svBusinessCategory() {
        return svImageStandardMapper.svBusinessCategory();
    }

    @Override
    public List<String> svAccountType(String businessCategory) {
        return svImageStandardMapper.svAccountType(businessCategory);
    }

    @Override
    public List<HashMap<String, Object>> certificateType(String businessCategory, String accountType) {
        return svImageStandardMapper.certificateType(businessCategory, accountType);
    }
}
