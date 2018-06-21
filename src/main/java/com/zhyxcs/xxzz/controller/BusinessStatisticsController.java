package com.zhyxcs.xxzz.controller;

import com.zhyxcs.xxzz.domain.GroundsForReturn;
import com.zhyxcs.xxzz.domain.Orga;
import com.zhyxcs.xxzz.domain.User;
import com.zhyxcs.xxzz.domain.WorkIndex;
import com.zhyxcs.xxzz.service.*;
import com.zhyxcs.xxzz.utils.AuditStatus;
import com.zhyxcs.xxzz.utils.CramsConstants;
import com.zhyxcs.xxzz.utils.OrgaLevelEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api/statistics")
public class BusinessStatisticsController extends BaseController {
    @Autowired
    private BusinessStatisticsService businessStatisticsService;
    @Autowired
    private ImageStandardService imageStandardService;
    @Autowired
    private GroundsForReturnService groundsForReturnService;
    @Autowired
    private OrgaService orgaService;
    @Autowired
    private WorkIndexService workIndexService;

    /**
     * @描述:业务量统计
     * @param:pbcCode(归属人民银行代码)
     * @param:areaCode(地区代码)
     * @param:cityCode(城市代码)
     * @param:bankKind(机构类别代码)
     * @param:bankType(机构行别代码)
     * @param:bankCode(金融机构代码)
     * @param:bankCode(金融机构代码)
     * @params:startTime(统计的开始时间)
     * @params:endTime(统计的结束时间)
     * @return：Map
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
        writeLog("账户业务量统计");
        pbcCode = ((pbcCode == null || "".equals(pbcCode.trim())) ? null : pbcCode);
        areaCode = ((areaCode == null || "".equals(areaCode.trim())) ? null : areaCode);
        cityCode = ((cityCode == null || "".equals(cityCode.trim())) ? null : cityCode);
        bankKind = ((bankKind == null || "".equals(bankKind.trim())) ? null : bankKind);
        bankType = ((bankType == null || "".equals(bankType.trim())) ? null : bankType);
        bankCode = ((bankCode == null || "".equals(bankCode.trim())) ? null : bankCode);
        startTime = (startTime == null ? null : startTime);
        endTime = (endTime == null ? null : endTime);

        List<String> pbcCodeList = null;
        List<String> commerceCodeList = null;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(CramsConstants.SESSION_LOGIN_USER);
        String currentUserBankCode = user.getSbankcode();
        OrgaLevelEnum orgaLevelEnum = orgaService.getOrgaLevel(currentUserBankCode);
        if (orgaLevelEnum == OrgaLevelEnum.BANKOFCOMMERCE) {
            commerceCodeList = getCodeList(currentUserBankCode);
            pbcCodeList = null;
        } else {
            pbcCodeList = getCodeList(currentUserBankCode);
            commerceCodeList = null;
        }

        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<MeasureBean> measureBeanList = packMeasure();
        for (MeasureBean bean : measureBeanList) {
            int total = businessStatisticsService.measureBusiness(pbcCode, areaCode, cityCode, bankKind, bankType, bankCode, startTime, endTime, bean.getBusinessCategory(), bean.getAccountType(), null, pbcCodeList, commerceCodeList);
            int approval = businessStatisticsService.measureBusiness(pbcCode, areaCode, cityCode, bankKind, bankType, bankCode, startTime, endTime, bean.getBusinessCategory(), bean.getAccountType(), AuditStatus.APPROVAL.getValue(), pbcCodeList, commerceCodeList);
            int untread = businessStatisticsService.measureBusiness(pbcCode, areaCode, cityCode, bankKind, bankType, bankCode, startTime, endTime, bean.getBusinessCategory(), bean.getAccountType(), AuditStatus.UNTREAD.getValue(), pbcCodeList, commerceCodeList);
            bean.setTotal(total);
            bean.setApproval(approval);
            bean.setUntread(untread);
        }
        resultMap.put("measureResult", measureBeanList);
        return resultMap;
    }

    /**
     * @描述:差错统计
     * @param:pbcCode(归属人民银行代码)
     * @param:areaCode(地区代码)
     * @param:cityCode(城市代码)
     * @param:bankKind(机构类别代码)
     * @param:bankType(机构行别代码)
     * @param:bankCode(金融机构代码)
     * @param:bankCode(金融机构代码)
     * @params:startTime(统计的开始时间)
     * @params:endTime(统计的结束时间)
     * @return：Map
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
        writeLog("业务差错统计");
        pbcCode = ((pbcCode == null || "".equals(pbcCode.trim())) ? null : pbcCode);
        areaCode = ((areaCode == null || "".equals(areaCode.trim())) ? null : areaCode);
        cityCode = ((cityCode == null || "".equals(cityCode.trim())) ? null : cityCode);
        bankKind = ((bankKind == null || "".equals(bankKind.trim())) ? null : bankKind);
        bankType = ((bankType == null || "".equals(bankType.trim())) ? null : bankType);
        bankCode = ((bankCode == null || "".equals(bankCode.trim())) ? null : bankCode);
        startTime = (startTime == null ? null : startTime);
        endTime = (endTime == null ? null : endTime);

        List<String> pbcCodeList = null;
        List<String> commerceCodeList = null;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(CramsConstants.SESSION_LOGIN_USER);
        String currentUserBankCode = user.getSbankcode();
        OrgaLevelEnum orgaLevelEnum = orgaService.getOrgaLevel(currentUserBankCode);
        if (orgaLevelEnum == OrgaLevelEnum.BANKOFCOMMERCE) {
            commerceCodeList = getCodeList(currentUserBankCode);
            pbcCodeList = null;
        } else {
            pbcCodeList = getCodeList(currentUserBankCode);
            commerceCodeList = null;
        }

        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<MistakeBean> mistakeBeanList = packMistake();
        for (MistakeBean bean : mistakeBeanList) {
            int untread = businessStatisticsService.mistakeBusiness(pbcCode, areaCode, cityCode, bankKind, bankType, bankCode, startTime, endTime, bean.getBusinessCategory(), bean.getGrounds(), pbcCodeList, commerceCodeList);
            bean.setUntread(untread);
        }
        resultMap.put("mistakeResult", mistakeBeanList);
        return resultMap;
    }

    @RequestMapping(value = "/diaryprint", method = RequestMethod.GET)
    public Map<String, Object> diaryPrint(@RequestParam(value = "startTime", required = false) Date startTime) {
        writeLog("打印日计表");
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (startTime == null) {
            resultMap.put("warn", "请选择查询条件的开始时间作为日计表的统计时间");
            return resultMap;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(CramsConstants.SESSION_LOGIN_USER);
        if (user == null || !"0".equals(user.getSbankcode().substring(0, 1))) {
            resultMap.put("warn", "仅人民银行可打印账户日计表");
            return resultMap;
        }

        List<WorkIndex> workIndexList = workIndexService.queryDiary(user.getSbankcode(), startTime);
        if (workIndexList == null || workIndexList.size() == 0) {
            resultMap.put("warn", "选择的日期没有对应的日计表数据");
            return resultMap;
        }

        List<DiaryBean> diaryBeanList = new ArrayList<DiaryBean>();
        for (int i = 0; i < workIndexList.size(); i++) {
            DiaryBean bean = new DiaryBean();
            bean.setIndex(i + 1);
            bean.setDepositorName(workIndexList.get(i).getSdepositorname());
            bean.setBankCode(workIndexList.get(i).getSbankcode());
            bean.setBankName(workIndexList.get(i).getSbankname());
            bean.setBusinessCategory(workIndexList.get(i).getSbusinesscategory());
            String tempApprovalCode = workIndexList.get(i).getSapprovalcode();
            tempApprovalCode = (tempApprovalCode == null || "".equals(tempApprovalCode.trim())) ? "" : tempApprovalCode;
            bean.setApprovalCode(tempApprovalCode);
            bean.setBankSignIn(null);
            diaryBeanList.add(bean);
        }
        resultMap.put("list", diaryBeanList);
        return resultMap;
    }

    private List<MeasureBean> packMeasure() {
        List<String> businessCategoryList = imageStandardService.businessCategory();
        if (businessCategoryList == null) {
            return null;
        }
        List<MeasureBean> list = new ArrayList<>();
        for (String businessCategory : businessCategoryList) {
            List<String> accountTypeList = imageStandardService.accountType(businessCategory);
            for (String accountType : accountTypeList) {
                MeasureBean bean = new MeasureBean();
                bean.setBusinessCategory(businessCategory);
                bean.setAccountType(accountType);
                bean.setTotal(0);
                bean.setApproval(0);
                bean.setUntread(0);
                list.add(bean);
            }
        }
        return list;
    }

    private List<MistakeBean> packMistake() {
        List<String> businessCategoryList = imageStandardService.businessCategory();
        if (businessCategoryList == null) {
            return null;
        }
        List<GroundsForReturn> groundsForReturnList = groundsForReturnService.selectAll();
        List<MistakeBean> mistakeBeanList = new ArrayList<MistakeBean>();
        for (String businessCategory : businessCategoryList) {
            for (GroundsForReturn groundsForReturn : groundsForReturnList) {
                MistakeBean bean = new MistakeBean();
                bean.setBusinessCategory(businessCategory);
                bean.setGrounds(groundsForReturn.getSgrounds());
                bean.setUntread(0);
                mistakeBeanList.add(bean);
            }
        }
        return mistakeBeanList;
    }

    private List<String> getCodeList(String currentUserBankCode) {
        return orgaService.getUnderBankcodeList(currentUserBankCode);
    }
}

class MeasureBean {
    //业务类别
    private String businessCategory;
    //账户种类
    private String accountType;
    //业务总数
    private Integer total;
    //业务通过数
    private Integer approval;
    //业务退回数
    private Integer untread;

    public MeasureBean() {

    }

    public MeasureBean(String businessCategory, String accountType, Integer total, Integer approval, Integer untread) {
        this.businessCategory = businessCategory;
        this.accountType = accountType;
        this.total = total;
        this.approval = approval;
        this.untread = untread;
    }

    public String getBusinessCategory() {
        return businessCategory;
    }

    public void setBusinessCategory(String businessCategory) {
        this.businessCategory = businessCategory;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getApproval() {
        return approval;
    }

    public void setApproval(Integer approval) {
        this.approval = approval;
    }

    public Integer getUntread() {
        return untread;
    }

    public void setUntread(Integer untread) {
        this.untread = untread;
    }
}

class MistakeBean {
    //业务类别
    private String businessCategory;
    //业务退回理由
    private String grounds;
    //业务退回数
    private Integer untread;

    public MistakeBean() {
    }

    public MistakeBean(String businessCategory, String grounds, Integer untread) {
        this.businessCategory = businessCategory;
        this.grounds = grounds;
        this.untread = untread;
    }

    public String getBusinessCategory() {
        return businessCategory;
    }

    public void setBusinessCategory(String businessCategory) {
        this.businessCategory = businessCategory;
    }

    public String getGrounds() {
        return grounds;
    }

    public void setGrounds(String grounds) {
        this.grounds = grounds;
    }

    public Integer getUntread() {
        return untread;
    }

    public void setUntread(Integer untread) {
        this.untread = untread;
    }
}

class DiaryBean {
    private int index;
    private String depositorName;
    private String bankCode;
    private String bankName;
    private String businessCategory;
    private String approvalCode;
    private String bankSignIn;

    public DiaryBean() {
    }

    public DiaryBean(int index, String depositorName, String bankCode, String bankName, String businessCategory, String approvalCode, String bankSignIn) {
        this.index = index;
        this.depositorName = depositorName;
        this.bankCode = bankCode;
        this.bankName = bankName;
        this.businessCategory = businessCategory;
        this.approvalCode = approvalCode;
        this.bankSignIn = bankSignIn;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getDepositorName() {
        return depositorName;
    }

    public void setDepositorName(String depositorName) {
        this.depositorName = depositorName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBusinessCategory() {
        return businessCategory;
    }

    public void setBusinessCategory(String businessCategory) {
        this.businessCategory = businessCategory;
    }

    public String getApprovalCode() {
        return approvalCode;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }

    public String getBankSignIn() {
        return bankSignIn;
    }

    public void setBankSignIn(String bankSignIn) {
        this.bankSignIn = bankSignIn;
    }
}
