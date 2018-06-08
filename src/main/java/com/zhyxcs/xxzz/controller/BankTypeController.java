package com.zhyxcs.xxzz.controller;

import com.zhyxcs.xxzz.domain.BankKind;
import com.zhyxcs.xxzz.domain.BankType;
import com.zhyxcs.xxzz.service.BankTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/banktype")
public class BankTypeController {
    @Autowired
    BankTypeService bankTypeService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/business", method = RequestMethod.GET)
    public List<BankType> getBusinessBankKind() {

        return bankTypeService.selectAllBusinessBank();
    }

    @RequestMapping(value = "/businesses", method = RequestMethod.GET)
    public List<BankType> getAllBankKind() {
        return bankTypeService.selectAll();
    }


    @RequestMapping(value = "/getTypesByBankKind", method = RequestMethod.GET)
    public List<BankType> getTypesByBankKind(@RequestParam("bankKindCode") String bankKindCode) {
        return bankTypeService.getTypesByBankKind(bankKindCode);
    }
}
