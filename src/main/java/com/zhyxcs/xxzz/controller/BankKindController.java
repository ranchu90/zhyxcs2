package com.zhyxcs.xxzz.controller;

import com.zhyxcs.xxzz.domain.BankKind;
import com.zhyxcs.xxzz.service.BankKindService;
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

    @RequestMapping(value = "/bankKind", method = RequestMethod.GET)
    public List<BankKind> getAll(){
        return bankKindService.selectAll();
    }
}
