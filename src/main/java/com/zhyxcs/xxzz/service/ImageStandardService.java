package com.zhyxcs.xxzz.service;

import java.util.List;

public interface ImageStandardService {
    List<String> businessCatagory();

    List<String> accountType();

    List<String> certificateType(String businessCatagory, String accountType);
}
