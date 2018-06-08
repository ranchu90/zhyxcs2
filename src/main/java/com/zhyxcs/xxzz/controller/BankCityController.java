package com.zhyxcs.xxzz.controller;

import com.zhyxcs.xxzz.domain.BankArea;
import com.zhyxcs.xxzz.domain.BankCity;
import com.zhyxcs.xxzz.service.BankAreaService;
import com.zhyxcs.xxzz.service.BankCityService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bankCity")
public class BankCityController {
    @Autowired
    private BankCityService bankCityService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/bankCity", method = RequestMethod.GET)
    public List<BankCity> getAll(@RequestParam("bankAreaCode") String bankAreaCode){
        List<BankCity> list = null;

        try {
            list = bankCityService.selectByArea(bankAreaCode);
        } catch (Exception e){
            e.printStackTrace();
            logger.error("## Error Information ##: {}", e);
        }
        return list;
    }

}
