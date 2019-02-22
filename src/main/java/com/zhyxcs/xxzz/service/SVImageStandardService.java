package com.zhyxcs.xxzz.service;

import java.util.List;

public interface SVImageStandardService {
    List<String> svBusinessCategory();

    List<String> svAccountType(String businessCategory);
}
