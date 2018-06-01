package com.zhyxcs.xxzz.service;

import java.util.HashMap;
import java.util.List;

public interface ImageStandardService {
    List<String> businessCategory();

    List<String> accountType(String businessCategory);

    List<HashMap<String, Object>> certificateType(String businessCategory, String accountType);
}
