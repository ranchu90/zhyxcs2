package com.zhyxcs.xxzz.controller;

import com.zhyxcs.xxzz.domain.Orga;
import com.zhyxcs.xxzz.service.OrgaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orga")
public class OrgaController {
    @Autowired
    private OrgaService orgaService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/orga", method = RequestMethod.GET)
    public List<Orga> getByBankTypeAndPbcCode(@RequestParam("pbcCode") String pbcCode,
                                              @RequestParam("bankTypeCode") String bankTypeCode) {

        return orgaService.selectByBankTypeAndPbcCode(pbcCode, bankTypeCode);
    }

    @RequestMapping(value = "/orgaWithKindAndPbcCode", method = RequestMethod.GET)
    public List<Orga> getByBankKindAndPbcCode(@RequestParam("pbcCode") String pbcCode,
                                              @RequestParam("bankKind") String bankKind) {
        return orgaService.selectByBankKindAndPbcCode(pbcCode, bankKind);
    }

    @RequestMapping(value = "/getByFullConditions", method = RequestMethod.GET)
    public List<Orga> getByFullConditions(@RequestBody Orga orga) {
        return orgaService.getByFullConditions(orga);
    }

    @RequestMapping(value = "/getPBCList", method = RequestMethod.GET)
    public List<Orga> getPBCList() {
        return orgaService.getPBCList();
    }

    @RequestMapping(value = "/bankCode", method = RequestMethod.GET)
    public Orga getBankCityCodeByBankCode( @RequestParam("bankCode") String bankCode){
        return orgaService.selectByPrimaryKey(bankCode);
    }
}
