package com.zhyxcs.xxzz.controller;

import com.zhyxcs.xxzz.service.ImageStandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class ImageStandardController {
    @Autowired
    private ImageStandardService imageStandardService;

    @RequestMapping(value = "businessCategory", method = RequestMethod.GET)
    public List<String> getBusinessCategory(){
        return imageStandardService.businessCatagory();
    }

    @RequestMapping(value = "accountType", method = RequestMethod.GET)
    public List<String> getAccountType(){
        return imageStandardService.accountType();
    }
}
