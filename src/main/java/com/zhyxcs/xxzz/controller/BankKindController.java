package com.zhyxcs.xxzz.controller;

import com.zhyxcs.xxzz.domain.BankKind;
import com.zhyxcs.xxzz.service.BankKindService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bankKind")
public class BankKindController {
    @Autowired
    private BankKindService bankKindService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/bankKind", method = RequestMethod.GET)
    public List<BankKind> getAll(){
        List<BankKind> list = null;

        try {
            list = bankKindService.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("## Error Information ##: {}", e);
        }
        return list;
    }
}
