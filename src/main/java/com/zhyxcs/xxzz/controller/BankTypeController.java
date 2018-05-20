package com.zhyxcs.xxzz.controller;

import com.zhyxcs.xxzz.domain.BankKind;
import com.zhyxcs.xxzz.domain.BankType;
import com.zhyxcs.xxzz.service.BankTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/banktype")
public class BankTypeController {
    @Autowired
    BankTypeService bankTypeService;

    @RequestMapping(value = "/business", method = RequestMethod.GET)
    public List<BankType> getBusinessBankKind(){

        return bankTypeService.selectAllBusinessBank();
    }
}
