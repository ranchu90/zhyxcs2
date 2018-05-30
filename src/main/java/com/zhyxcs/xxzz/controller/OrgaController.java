package com.zhyxcs.xxzz.controller;

import com.zhyxcs.xxzz.domain.Orga;
import com.zhyxcs.xxzz.service.OrgaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orga")
public class OrgaController {
    @Autowired
    private OrgaService orgaService;

    @RequestMapping(value = "/orga", method = RequestMethod.GET)
    public List<Orga> getByBankTypeAndPbcCode(@RequestParam("pbcCode") String pbcCode,
                                              @RequestParam("bankTypeCode") String bankTypeCode){

        return orgaService.selectByBankTypeAndPbcCode(pbcCode, bankTypeCode);
    }

    @RequestMapping(value = "/orgaWithKindAndPbcCode", method = RequestMethod.GET)
    public List<Orga> getByBankKindAndPbcCode(@RequestParam("pbcCode") String pbcCode,
                                              @RequestParam("bankKind") String bankKind){

        return orgaService.selectByBankKindAndPbcCode(pbcCode, bankKind);
    }
}
