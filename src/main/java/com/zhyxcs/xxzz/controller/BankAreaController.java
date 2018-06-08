package com.zhyxcs.xxzz.controller;

import com.zhyxcs.xxzz.domain.BankArea;
import com.zhyxcs.xxzz.service.BankAreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bankArea")
public class BankAreaController {
    @Autowired
    private BankAreaService bankAreaService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/bankArea", method = RequestMethod.GET)
    public List<BankArea> getAll(){
        List<BankArea> list = null;

        try {
            list = bankAreaService.selectAll();
        } catch (Exception e){
            e.printStackTrace();
            logger.error("## Error Information ##: {}", e);
        }
        return list;
    }
}
