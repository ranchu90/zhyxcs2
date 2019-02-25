package com.zhyxcs.xxzz.service;

import java.util.HashMap;
import java.util.List;

public interface SVImageStandardService {
    List<String> svBusinessCategory();

    List<String> svAccountType(String businessCategory);

    List<HashMap<String, Object>> certificateType(String businessCategory, String accountType);
}
