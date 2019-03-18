package com.zhyxcs.xxzz.controller.sv;

import com.zhyxcs.xxzz.controller.BaseController;
import com.zhyxcs.xxzz.service.SVImageStandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/svimages")
public class SVImageStandardController extends BaseController {
    @Autowired
    SVImageStandardService svImageStandardService;

    @RequestMapping(value = "/basicCategory", method = RequestMethod.GET)
    public List<HashMap> getBasicCategory(){

        List <HashMap> resultList = new ArrayList();
        List <String> businessCategoryList = svImageStandardService.svBusinessCategory();

        for (String businessCategory : businessCategoryList){
            List<String> accountType = svImageStandardService.svAccountType(businessCategory);

            HashMap map = new HashMap();
            map.put("businessCategory", businessCategory);
            map.put("accountType", accountType);

            resultList.add(map);
        }

        return resultList;
    }

    @RequestMapping(value = "/certificateType", method = RequestMethod.GET)
    public List<HashMap<String, Object>> getCertificateType(@RequestParam(value = "businessCategory") String businessCategory,
                                                            @RequestParam(value = "accountType") String accountType){
        return svImageStandardService.certificateType(businessCategory, accountType);
    }

    @RequestMapping(value = "/businessCategory", method = RequestMethod.GET)
    public List<String> getBusinessCategory(){
        return svImageStandardService.svBusinessCategory();
    }
}
