package com.zhyxcs.xxzz.controller;

import com.zhyxcs.xxzz.service.ImageStandardService;
import com.zhyxcs.xxzz.utils.CramsConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/images")
public class ImageStandardController extends BaseController {
    @Autowired
    private ImageStandardService imageStandardService;

    @RequestMapping(value = "/businessCategory", method = RequestMethod.GET)
    public List<String> getBusinessCategory(){
        return imageStandardService.businessCatagory();
    }

    @RequestMapping(value = "/accountType", method = RequestMethod.GET)
    public List<String> getAccountType(){
        return imageStandardService.accountType();
    }

    @RequestMapping(value = "/certificateType", method = RequestMethod.GET)
    public List<String> getCertificateType(@RequestParam(value = "businessCatagory") String businessCatagory,
                                           @RequestParam(value = "accountType") String accountType){
        return imageStandardService.certificateType(businessCatagory, accountType);
    }
}
