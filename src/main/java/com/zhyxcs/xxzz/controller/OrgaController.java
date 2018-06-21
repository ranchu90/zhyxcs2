package com.zhyxcs.xxzz.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhyxcs.xxzz.domain.Orga;
import com.zhyxcs.xxzz.service.OrgaService;
import com.zhyxcs.xxzz.service.UserService;
import com.zhyxcs.xxzz.service.WorkIndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orga")
public class OrgaController extends BaseController {
    @Autowired
    private OrgaService orgaService;
    @Autowired
    private UserService userService;
    @Autowired
    private WorkIndexService workIndexService;

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

    @RequestMapping(value = "/getNextOrgaIsOnCountByBankCode", method = RequestMethod.GET)
    public int getNextOrgaIsOnCountByBankCode(@RequestParam("bankCode") String bankCode) {
        return orgaService.calculateNextOrgaNum(bankCode);
    }

    @RequestMapping(value = "/getPBCList", method = RequestMethod.GET)
    public List<Orga> getPBCList() {
        return orgaService.getPBCList();
    }

    @RequestMapping(value = "/bankCode", method = RequestMethod.GET)
    public Orga getBankCityCodeByBankCode(@RequestParam("bankCode") String bankCode) {
        return orgaService.selectByPrimaryKey(bankCode);
    }

    @RequestMapping(value = "/pageorga", method = RequestMethod.GET)
    public Map<String, Object> pageOrgaWithConditions(@RequestParam(value = "pageSize", required = false) String pageSize,
                                                      @RequestParam(value = "pageNum", required = false) String pageNum,
                                                      @RequestParam(value = "bankAreaCode", required = false) String bankAreaCode,
                                                      @RequestParam(value = "bankCityCode", required = false) String bankCityCode,
                                                      @RequestParam(value = "bankKind", required = false) String bankKind,
                                                      @RequestParam(value = "bankTypeCode", required = false) String bankTypeCode,
                                                      @RequestParam(value = "topBankCode", required = false) String topBankCode,
                                                      @RequestParam(value = "pbcode", required = false) String pbcode,
                                                      @RequestParam(value = "bankCode", required = false) String bankCode,
                                                      @RequestParam(value = "bankName", required = false) String bankName,
                                                      @RequestParam(value = "bankState", required = false) String bankState) {
        Map<String, Object> map = new HashMap<String, Object>();
        pageSize = (pageSize == null || "".equals(pageSize.trim())) ? this.getDisplayCount() : pageSize;
        pageNum = (pageNum == null || "".equals(pageNum.trim())) ? "1" : pageNum;
        bankAreaCode = (bankAreaCode == null || "".equals(bankAreaCode.trim())) ? null : bankAreaCode;
        bankCityCode = (bankCityCode == null || "".equals(bankCityCode.trim())) ? null : bankCityCode;
        bankKind = (bankKind == null || "".equals(bankKind.trim())) ? null : bankKind;
        bankTypeCode = (bankTypeCode == null || "".equals(bankTypeCode.trim())) ? null : bankTypeCode;
        topBankCode = (topBankCode == null || "".equals(topBankCode.trim())) ? null : topBankCode;
        pbcode = (pbcode == null || "".equals(pbcode.trim())) ? null : pbcode;
        bankCode = (bankCode == null || "".equals(bankCode.trim())) ? null : bankCode;
        bankName = (bankName == null || "".equals(bankName.trim())) ? null : bankName;
        bankState = (bankState == null || "".equals(bankState.trim())) ? null : bankState;

        PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
        List<Orga> orgaList = orgaService.pageOrgaWithConditions(bankAreaCode, bankCityCode, bankKind, bankTypeCode, topBankCode, pbcode, bankCode, bankName, bankState);
        PageInfo<Orga> pageInfo = new PageInfo<>(orgaList);
        map.put("pageInfo", pageInfo);
        return map;
    }

    @RequestMapping(value = "/removeOrgaList", method = RequestMethod.GET)
    public Map<String, Object> removeOrgaList(@RequestParam("bankCodes") String bankCodes) {
        Map<String, Object> map = new HashMap<String, Object>();
        String[] bankCodeArray = (bankCodes == null || "".equals(bankCodes.trim())) ? null : bankCodes.split(",");
        for (String bankCode : bankCodeArray) {
            if (hasNextOrga(bankCode)) {
                map.put("warn", "机构存在下级机构,请删除下级机构");
                return map;
            }
            if (hasBusinessHappen(bankCode)) {
                map.put("warn", "机构曾发生过影像业务,请删除机构所关联的影像业务");
                return map;
            }
        }
        if (hasUsers(bankCodeArray)) {
            map.put("warn", "机构下存在用户,请删除机构对应的用户");
            return map;
        }
        try {
            orgaService.deleteByBankCodeArray(bankCodeArray);
            map.put("success", "机构删除成功");
        } catch (Exception e) {
            throw e;
        } finally {
            writeLog("删除机构{bankCode:" + bankCodeArray.toString() + "}");
        }

        return map;
    }

    private boolean hasNextOrga(String bankCode) {
        int nextOrgaNum = orgaService.calculateNextOrgaNum(bankCode);
        if (nextOrgaNum > 0) {
            return true;
        }
        return false;
    }

    private boolean hasUsers(String[] bankCodeArray) {
        int users = userService.calculateUsersByBankCodeArray(bankCodeArray);
        if (users > 0) {
            return true;
        }
        return false;
    }

    private boolean hasBusinessHappen(String bankCode) {
        int works = workIndexService.calculateWorksByBankCode(bankCode);
        if (works > 0) {
            return true;
        }
        return false;
    }

    @RequestMapping(value = "/addorga", method = RequestMethod.POST)
    public int addOrga(@RequestBody Orga orga) {
        int affectedRecords = 0;
        if (orga == null) {
            return affectedRecords;
        }

        orga.setSbankstate("0");

        try {
            affectedRecords = orgaService.insert(orga);
        } catch (Exception e) {
            throw e;
        } finally {
            writeLog("添加机构{bankCode:" + orga.getSbankcode() + "}");
        }
        return affectedRecords;
    }

    @RequestMapping(value = "/updateorga", method = RequestMethod.POST)
    public int updateOrga(@RequestBody Orga orga) {
        int affectedRecords = 0;
        if (orga == null) {
            return affectedRecords;
        }

        try {
            affectedRecords = orgaService.updateByPrimaryKeyCheckPropertyIsNull(orga);
        } catch (Exception e) {
            throw e;
        } finally {
            writeLog("修改机构{bankCode:" + orga.getSbankcode() + "}");
        }
        return affectedRecords;
    }

}
