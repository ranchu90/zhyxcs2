package com.zhyxcs.xxzz.service;

import java.util.List;

public interface ImageStandardService {
    List<String> businessCategory();

    List<String> accountType(String businessCategory);

    List<String> certificateType(String businessCategory, String accountType);
}
