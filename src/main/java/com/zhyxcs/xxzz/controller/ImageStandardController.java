package com.zhyxcs.xxzz.controller;

import com.zhyxcs.xxzz.service.ImageStandardService;
import com.zhyxcs.xxzz.utils.CramsConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImageStandardController extends BaseController {
    @Autowired
    private ImageStandardService imageStandardService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/businessCategory", method = RequestMethod.GET)
    public List<String> getBusinessCategory(){
        return imageStandardService.businessCategory();
    }

    @RequestMapping(value = "/accountType", method = RequestMethod.GET)
    public List<String> getAccountType(@RequestParam(value = "businessCategory") String businessCategory){
        return imageStandardService.accountType(businessCategory);
    }

    @RequestMapping(value = "/certificateType", method = RequestMethod.GET)
    public List<HashMap<String, Object>> getCertificateType(@RequestParam(value = "businessCategory") String businessCategory,
                                           @RequestParam(value = "accountType") String accountType){
        return imageStandardService.certificateType(businessCategory, accountType);
    }

    @RequestMapping(value = "/basicCategory", method = RequestMethod.GET)
    public List<HashMap> getBasicCategory(){
        List <HashMap> resultList = new ArrayList();
        List <String> businessCategoryList = imageStandardService.businessCategory();

        for (String businessCategory : businessCategoryList){
            List<String> accountType = imageStandardService.accountType(businessCategory);

            HashMap map = new HashMap();
            map.put("businessCategory", businessCategory);
            map.put("accountType", accountType);

            resultList.add(map);
        }


        return resultList;
    }
}
