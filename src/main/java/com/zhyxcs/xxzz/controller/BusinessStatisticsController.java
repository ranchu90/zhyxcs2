package com.zhyxcs.xxzz.controller;


import com.zhyxcs.xxzz.service.BusinessStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/api/statistics")
public class BusinessStatisticsController {
    @Autowired
    private BusinessStatisticsService businessStatisticsService;

    /**
     * @描述:业务量统计
     *@param:pbcCode(归属人民银行代码)
     *@param:areaCode(地区代码)
     *@param:cityCode(城市代码)
     *@param:bankKind(机构类别代码)
     *@param:bankType(机构行别代码)
     *@param:bankCode(金融机构代码)
     *@param:bankCode(金融机构代码)
     *@params:startTime(统计的开始时间)
     *@params:endTime(统计的结束时间)
     *@return：Map
     */
    @RequestMapping(value = "/measure", method = RequestMethod.GET)
    public Map<String, Object> measure(@RequestParam(value = "pbcCode", required = false) String pbcCode,
                                       @RequestParam(value = "areaCode", required = false) String areaCode,
                                       @RequestParam(value = "cityCode", required = false) String cityCode,
                                       @RequestParam(value = "bankKind", required = false) String bankKind,
                                       @RequestParam(value = "bankType", required = false) String bankType,
                                       @RequestParam(value = "bankCode", required = false) String bankCode,
                                       @RequestParam(value = "startTime", required = false) Date startTime,
                                       @RequestParam(value = "endTime", required = false) Date endTime) {


        return null;

    }

    /**
     * @描述:差错统计
     *@param:pbcCode(归属人民银行代码)
     *@param:areaCode(地区代码)
     *@param:cityCode(城市代码)
     *@param:bankKind(机构类别代码)
     *@param:bankType(机构行别代码)
     *@param:bankCode(金融机构代码)
     *@param:bankCode(金融机构代码)
     *@params:startTime(统计的开始时间)
     *@params:endTime(统计的结束时间)
     *@return：Map
     */
    @RequestMapping(value = "/mistake", method = RequestMethod.GET)
    public Map<String, Object> mistake(@RequestParam(value = "pbcCode", required = false) String pbcCode,
                                       @RequestParam(value = "areaCode", required = false) String areaCode,
                                       @RequestParam(value = "cityCode", required = false) String cityCode,
                                       @RequestParam(value = "bankKind", required = false) String bankKind,
                                       @RequestParam(value = "bankType", required = false) String bankType,
                                       @RequestParam(value = "bankCode", required = false) String bankCode,
                                       @RequestParam(value = "startTime", required = false) Date startTime,
                                       @RequestParam(value = "endTime", required = false) Date endTime) {
        return null;
    }
}
