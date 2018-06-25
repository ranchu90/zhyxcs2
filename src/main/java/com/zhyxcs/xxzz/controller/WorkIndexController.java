package com.zhyxcs.xxzz.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhyxcs.xxzz.config.ImageConfig;
import com.zhyxcs.xxzz.config.WordConfig;
import com.zhyxcs.xxzz.domain.*;
import com.zhyxcs.xxzz.service.*;
import com.zhyxcs.xxzz.utils.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

@RestController
@RequestMapping("/api/workIndex")
public class WorkIndexController extends BaseController{
    @Autowired
    private WorkIndexService workIndexService;

    @Autowired
    private OrgaService orgaService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private ImageConfig imageConfig;

    @Autowired
    private UserService userService;

    @Autowired
    private BusinessStatisticsService businessStatisticsService;

    @Autowired
    private WordConfig wordConfig;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/workIndex", method = RequestMethod.POST)
    public WorkIndex insert(@RequestBody WorkIndex workIndex){
        HttpSession session = super.request.getSession(false);
        User user = (User) session.getAttribute(CramsConstants.SESSION_LOGIN_USER);
        Orga orga = (Orga) session.getAttribute(CramsConstants.SESSION_ORGA_WITH_USER);

        if (orga != null && user !=null) {

            workIndex.setSbankcode(orga.getSbankcode());
            workIndex.setSbankname(orga.getSbankname());
            workIndex.setSupusername(user.getSusername());
            workIndex.setSupusercode(user.getSusercode());
            workIndex.setSbusinessemergency("0");

            try {
                workIndexService.newWorkIndex(workIndex);
            } catch (Exception e){
                e.printStackTrace();
                logger.error("## Error Information ##: {}", e);
            }

            this.writeLog(Logs.TRANS_NEW_SUCCESS);
            return workIndex;
        } else {
            this.writeLog(Logs.TRANS_NEW_FAILED);
        }


        return null;
    }

    @RequestMapping(value = "/workIndex", method = RequestMethod.GET)
    public Map<String,Object> getWorkIndexes(@RequestParam(value = "pageSize", required = false) String pageSize,
                                          @RequestParam(value = "pageNum", required = false) String pageNum,
                                          @RequestParam(value = "currentBankArea", required = false) String currentBankArea,
                                          @RequestParam(value = "currentCity", required = false) String currentCity,
                                          @RequestParam(value = "bankKind", required = false) String bankKind,
                                          @RequestParam(value = "bankType", required = false) String bankType,
                                          @RequestParam(value = "businessCategory", required = false) String businessCategory,
                                          @RequestParam(value = "accountType", required = false) String accountType,
                                          @RequestParam(value = "orgaCode", required = false) String orgaCode,
                                          @RequestParam(value = "bankEntryUserCode", required = false) String bankEntryUserCode,
                                          @RequestParam(value = "bankReviewUserCode", required = false) String bankReviewUserCode,
                                          @RequestParam(value = "renEntryUserCode", required = false) String renEntryUserCode,
                                          @RequestParam(value = "renRecheckUserCode", required = false) String renRecheckUserCode,
                                          @RequestParam(value = "transactionNum", required = false) String transactionNum,
                                          @RequestParam(value = "approvalCode", required = false) String approvalCode,
                                          @RequestParam(value = "identifier", required = false) String identifier,
                                          @RequestParam(value = "startTime", required = false) Date startTimeDate,
                                          @RequestParam(value = "endTime", required = false) Date endTimeDate){
        pageSize = (pageSize == null || "".equals(pageSize.trim())) ? this.getDisplayCount() : pageSize;
        pageNum = (pageNum == null || "".equals(pageNum.trim())) ? "1" : pageNum;
        currentBankArea = (currentBankArea == null || "".equals(currentBankArea)) ? null : currentBankArea;
        currentCity = (currentCity == null || "".equals(currentCity)) ? null : currentCity;
        bankKind = (bankKind == null || "".equals(bankKind)) ? null : bankKind;
        bankType = (bankType == null || "".equals(bankType)) ? null : bankType;
        businessCategory = (businessCategory == null || "".equals(businessCategory)) ? null : businessCategory;
        accountType = (accountType == null || "".equals(accountType)) ? null : accountType;
        orgaCode = (orgaCode == null || "".equals(orgaCode)) ? null : orgaCode;
        bankEntryUserCode = (bankEntryUserCode == null || "".equals(bankEntryUserCode)) ? null : bankEntryUserCode;
        bankReviewUserCode = (bankReviewUserCode == null || "".equals(bankReviewUserCode)) ? null : bankReviewUserCode;
        renEntryUserCode = (renEntryUserCode == null || "".equals(renEntryUserCode)) ? null : renEntryUserCode;
        renRecheckUserCode = (renRecheckUserCode == null || "".equals(renRecheckUserCode)) ? null : renRecheckUserCode;
        transactionNum = (transactionNum == null || "".equals(transactionNum)) ? null : transactionNum;
        approvalCode = (approvalCode == null || "".equals(approvalCode)) ? null : approvalCode;
        identifier = (identifier == null || "".equals(identifier)) ? null : identifier;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String startTime = (startTimeDate == null) ? null : format.format(startTimeDate);
        String endTime = (endTimeDate == null) ? null : format.format(endTimeDate);


        HttpSession session = super.request.getSession();
        User user = (User) session.getAttribute(CramsConstants.SESSION_LOGIN_USER);
        Orga orga = (Orga) session.getAttribute(CramsConstants.SESSION_ORGA_WITH_USER);

        if (!"7".equals(user.getSuserlevel()) &&
                !"4".equals(user.getSuserlevel()) &&
                !"5".equals(user.getSuserlevel())){
            String bankCode = orga.getSbankcode();
            orgaCode = bankCode;
        }

        if ("1".equals(user.getSuserlevel())){
            bankEntryUserCode = user.getSusercode();
        }

        if ("2".equals(user.getSuserlevel())){
            bankReviewUserCode = user.getSusercode();
        }

        if ("4".equals(user.getSuserlevel())){
            renEntryUserCode = user.getSusercode();
        }

        if ("5".equals(user.getSuserlevel())){
            renRecheckUserCode = user.getSusercode();
        }

        Map<String, Object> map = new HashMap();

        try {
            PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
            List<WorkIndex> list = workIndexService.queryRecordByConditions(currentBankArea, currentCity, bankKind, bankType, businessCategory,
                    accountType, orgaCode, bankEntryUserCode, bankReviewUserCode, renEntryUserCode, renRecheckUserCode, transactionNum,
                    approvalCode, identifier, startTime, endTime);
            PageInfo<WorkIndex> workIndexPageInfo = new PageInfo(list);
            map.put("pageInfo", workIndexPageInfo);

            this.writeLog(Logs.TRANS_QUERY);
        } catch (Exception e){
            e.printStackTrace();
            logger.error("## Error Information ##: {}", e);
        }

        return map;
    }

    @RequestMapping(value = "/workIndex", method = RequestMethod.DELETE)
    public int deleteWorkIndexByTranID(@RequestParam(value = "stransactionnum") String stransactionnum){
        List<Image> imageList = imageService.selectImagesByTranID(stransactionnum);

        for (Image image : imageList){

            if (image != null){
                String path = image.getSstorepath();
                String basePath = imageConfig.getBasePath();
                File file = new File(basePath + path);

                if (file != null && file.delete()){
                    imageService.deleteByPrimaryKey(image.getSid());
                }
            }
        }

        this.writeLog(Logs.IMAGE_DELETE);
        this.writeLog(Logs.TRANS_DELETE);
        return workIndexService.deleteByPrimaryKey(stransactionnum);
    }

    @RequestMapping(value = "/occupy", method = RequestMethod.GET)
    public HashMap occupyTransaction(@RequestParam(value = "transactionNum") String transactionNum){
        HttpSession session = super.request.getSession(false);
        User user = (User) session.getAttribute(CramsConstants.SESSION_LOGIN_USER);
        String userLevel = user.getSuserlevel();
        String userBankCode = user.getSbankcode();
        String userCode = user.getSusercode();

        HashMap result = new HashMap();

        WorkIndex workIndex = workIndexService.selectByPrimaryKey(transactionNum);

        if (workIndex != null){
            switch (userLevel) {
                case "2":
                    String reviewUserCode = workIndex.getSreviewusercode();
                    if (!userBankCode.equals(workIndex.getSbankcode()) || (reviewUserCode!=null && !reviewUserCode.equals(userCode))) {
                        result.put("error", "你没有权限操作该笔业务！");
                        return result;
                    } else if (reviewUserCode == null) {
                        workIndex.setSreviewusercode(userCode);
                        workIndexService.occupyTransaction(workIndex);
                    }

                    break;
                case "4":
                    String checkUserCode = workIndex.getScheckusercode();
                    if (!userBankCode.equals(workIndex.getSpbcbankcode()) || (checkUserCode!=null && !checkUserCode.equals(userCode))) {
                        result.put("error", "你没有权限操作该笔业务！");
                        return result;
                    } else if (checkUserCode == null) {
                        workIndex.setScheckusercode(userCode);
                        workIndexService.occupyTransaction(workIndex);
                    }
                    break;
                case "5":
                    String reCheckUserCode = workIndex.getSrecheckusercode();
                    if (!userBankCode.equals(workIndex.getSpbcbankcode()) || (reCheckUserCode!=null && !reCheckUserCode.equals(userCode))) {
                        result.put("error", "你没有权限操作该笔业务！");
                        return result;
                    } else if (reCheckUserCode == null) {
                        workIndex.setSrecheckusercode(userCode);
                        workIndexService.occupyTransaction(workIndex);
                    }
                    break;
            }

            result.put("success", "success");
        } else {
            result.put("error", "该流水号业务不存在");
        }

        return result;
    }

    @RequestMapping(value = "/Depositor", method = RequestMethod.PUT)
    public int updateWorkIndexByDepositor(@RequestBody WorkIndex workIndex){
        this.writeLog(Logs.TRANS_UPDATE_DEPOSITOR);
        return workIndexService.updateDepositorNameByPrimaryKey(workIndex);
    }

    @RequestMapping(value = "/ApprovalState", method = RequestMethod.PUT)
    public int updateWorkIndexByApprovalState(@RequestBody WorkIndex workIndex,
                                              @RequestParam(value = "action") String action,
                                              @RequestParam(value = "groundsId", required = false) String groundsId,
                                              @RequestParam(value = "grounds", required = false) String grounds,
                                              @RequestParam(value = "groundsState", required = false) String groundsState){
        HttpSession session = super.request.getSession();
        User user = (User) session.getAttribute(CramsConstants.SESSION_LOGIN_USER);
        String userCode = user.getSusercode();
        String userName = user.getSusername();
        String level = user.getSuserlevel();

        WorkIndex tempWorkIndex = null;

        Date date = CommonUtils.newDate();
        try {
            switch (action) {
                case ActionType.COMMIT:
                    workIndex.setSendtime(date);
                    break;
                case ActionType.COMMIT_REN:
                    workIndex.setSendtime(date);
                    workIndex.setScommittimes(date);
                    break;
                case ActionType.CALL_BACK:
                    break;
                case ActionType.SEND_BACK:
                    workIndex.setSreturntimes(date);
                    break;
                case ActionType.SEND_BACK_REN:
                    workIndex.setSpbcreturntimes(date);
                    workIndex.setScheckusercode(userCode);
                    tempWorkIndex = workIndexService.selectByPrimaryKey(workIndex.getStransactionnum());
                    tempWorkIndex.setSpbcreturntimes(workIndex.getSpbcreturntimes());
                    //人民银行退回
                    businessStatisticsService.insert(tempWorkIndex, AuditStatus.UNTREAD, OvertimeStatus.NOOVER, new GroundsForReturn(Long.valueOf(groundsId), grounds, groundsState));
                    tempWorkIndex = null;
                    break;
                case ActionType.REVIEW:
                    workIndex.setSreviewusercode(userCode);
                    workIndex.setScommittimes(date);
                    break;
                case ActionType.CHECK:
                    workIndex.setScheckusercode(userCode);
                    tempWorkIndex = workIndexService.selectByPrimaryKey(workIndex.getStransactionnum());
                    tempWorkIndex.setScheckusercode(userCode);
                    //人民银行通过
                    businessStatisticsService.insert(tempWorkIndex, AuditStatus.APPROVAL, OvertimeStatus.NOOVER, null);
                    tempWorkIndex = null;
                    break;
                case ActionType.RECHECK:
                    workIndex.setSrecheckusercode(userCode);
                    workIndex.setSrecheckusername(userName);
                    workIndex.setSrechecktime(date);
                    break;
                case ActionType.UPLOAD_LICENCE:
                    workIndex.setSuploadlicense(1);
                    break;
                case ActionType.END:
                    workIndex.setScompletetimes(date);
                    workIndex.setScheckusercode(userCode);
                    tempWorkIndex = workIndexService.selectByPrimaryKey(workIndex.getStransactionnum());
                    tempWorkIndex.setScompletetimes(workIndex.getScompletetimes());
                    //人民银行终止
                    businessStatisticsService.insert(tempWorkIndex, AuditStatus.UNTREAD, OvertimeStatus.NOOVER, new GroundsForReturn(Long.valueOf(groundsId), grounds, groundsState));
                    tempWorkIndex = null;
                    break;
            }
        } catch (Exception e) {
            logger.error("## Error Information ##: {}", e);
        }

        this.writeLog(Logs.TRANS_UPDATE_APPROVALSTATE + ":" + action);

        int result = 0;

        try {
            result = workIndexService.updateApprovalStateNameByPrimaryKey(workIndex, action);
        } catch (Exception e) {
            logger.error("## Error Information ##: {}", e);
        }

        return result;
    }

    @RequestMapping(value = "/ApprovalCode", method = RequestMethod.PUT)
    public int updateWorkIndexByApprovalCodeAndIdentifier(@RequestBody WorkIndex workIndex,
                                                          @RequestParam(value = "expireTime", required = false) Date expireTime){
        if (expireTime != null) {
            workIndex.setSexpiretime(expireTime);
        }

        workIndex.setScompletetimes(CommonUtils.newDate());
        int code = 0;

        try {
            code = workIndexService.updateWorkIndexByApprovalCodeAndIdentifier(workIndex);
        } catch (Exception e) {
            logger.error("## Error Information ##: {}", e);
        }

        if (code <= 0){
            return 0;
        }

        this.writeLog(Logs.TRANS_UPDATE_APPROVALCODE_IDENTIFIER);
        return 1;
    }



    @RequestMapping(value = "/workIndexes", method = RequestMethod.GET)
    public HashMap<String, Object> getWorkIndexesByPage(@RequestParam(value = "pageSize") String pageSize,
                                                        @RequestParam(value = "currentPage") String currentPage,
                                                        @RequestParam(value = "approvalState") String approvalState,
                                                        @RequestParam(value = "businessEmergency") String businessEmergency,
                                                        @RequestParam(value = "ifUploadLicense", required = false) String ifUploadLicense,
                                                        @RequestParam(value = "ifRecheck", required = false) String ifRecheck){

        HttpSession session = super.request.getSession();
        User user = (User) session.getAttribute(CramsConstants.SESSION_LOGIN_USER);
        String currentBankCode = user.getSbankcode();
        String userLevel = user.getSuserlevel();
//        int totalPages = workIndexService.queryRecordTotalNum(user.getSusercode(), approvalState,
//                businessEmergency, userLevel, currentBankCode, ifUploadLicense, ifRecheck);

        List<WorkIndex> workIndexList = null;

        pageSize = (pageSize == null || "".equals(pageSize.trim())) ? this.getDisplayCount() : pageSize;
        currentPage = (currentPage == null || "".equals(currentPage.trim())) ? "1" : currentPage;

        Map<String, Object> mapTest = new HashMap();
        PageHelper.startPage(Integer.parseInt(currentPage), Integer.parseInt(pageSize));

        try {
            //判断用户的查询权限，根据用户级别
            switch (userLevel) {
                case "1":
                    workIndexList = workIndexService.queryRecordByPageAndUserCodeBankEntry(pageSize,
                            currentPage, user.getSusercode(), approvalState, userLevel, businessEmergency, currentBankCode);
                    break;
                case "2":
                    workIndexList = workIndexService.queryRecordByPageAndUserCodeBankCharge(pageSize,
                            currentPage, user.getSusercode(), approvalState, userLevel, businessEmergency, currentBankCode);
                    break;
                case "4":
                    workIndexList = workIndexService.queryRecordByPageAndUserCodeRenEntry(pageSize,
                            currentPage, user.getSusercode(), approvalState, userLevel, businessEmergency, currentBankCode, ifUploadLicense, ifRecheck);
                    break;
                case "5":
                    workIndexList = workIndexService.queryRecordByPageAndUserCodeRenCharge(pageSize,
                            currentPage, user.getSusercode(), approvalState, userLevel, businessEmergency, currentBankCode, ifUploadLicense, ifRecheck);
                    break;
                case "7":
                    workIndexList = workIndexService.queryRecordByPageAndUserCodeRenAdmin(pageSize,
                            currentPage, user.getSusercode(), approvalState, userLevel, businessEmergency);
                    break;
            }
        } catch (Exception e) {
            logger.error("## Error Information ##: {}", e);
        }

        PageInfo<WorkIndex> pageInfo = new PageInfo(workIndexList);
        mapTest.put("pageInfo", pageInfo);

        List<WorkIndex> tempList = pageInfo.getList();

        List<Object> newWorkIndexList = new ArrayList<Object>();

        for (WorkIndex workIndex : tempList){
            String approvelState = workIndex.getSapprovalstate();

            HashMap<String,Object> workTemp = new HashMap<String, Object>();
            Date recheckDate = workIndex.getSrechecktime();
            Date startDate = workIndex.getSstarttime();
            Date completeDate = workIndex.getScompletetimes();
            Date endDate = workIndex.getSendtime();

            workTemp.put("srechecktime", recheckDate);
            workTemp.put("sstarttime", startDate);
            workTemp.put("scompletetimes", completeDate);
            workTemp.put("sendtime", endDate);
            workTemp.put("sapprovalstate", this.approvalState(approvelState));
            workTemp.put("sapprovalcode", workIndex.getSapprovalcode());
            workTemp.put("saccounttype", workIndex.getSaccounttype());
            workTemp.put("sbankcode", workIndex.getSbankcode());
            workTemp.put("sbankname", workIndex.getSbankname());
            workTemp.put("sbusinesscategory", workIndex.getSbusinesscategory());
            workTemp.put("scheckusercode", workIndex.getScheckusercode());
            workTemp.put("sdepositorname", workIndex.getSdepositorname());
            workTemp.put("sidentifier", workIndex.getSidentifier());
            workTemp.put("srecheckopinion", workIndex.getSrecheckopinion());
            workTemp.put("srecheckresult", workIndex.getSrecheckresult());
            workTemp.put("srecheckusercode", workIndex.getSrecheckusercode());
            workTemp.put("srecheckusername", workIndex.getSrecheckusername());
            workTemp.put("sreviewusercode", workIndex.getSreviewusercode());
            workTemp.put("stransactionnum", workIndex.getStransactionnum());
            workTemp.put("supusercode", workIndex.getSupusercode());
            workTemp.put("supusername", workIndex.getSupusername());
            workTemp.put("sreturntimes", workIndex.getSreturntimes());
            workTemp.put("sbusinessemergency", workIndex.getSbusinessemergency());
            workTemp.put("suploadlicence", workIndex.getSuploadlicense());
            workTemp.put("sifneedlicence", workIndex.getSifneedlicence());
            workTemp.put("sexpiretime", workIndex.getSexpiretime());

            newWorkIndexList.add(workTemp);
        }

        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put("totalPages", pageInfo.getTotal());
        map.put("workIndexList", newWorkIndexList);

        return map;
    }

    @RequestMapping(value = "/workIndexNum", method = RequestMethod.GET)
    public int queryRecordTotalNum(@RequestParam(value = "approvalState") String approvalState,
                                   @RequestParam(value = "businessEmergency") String businessEmergency,
                                   @RequestParam(value = "ifUploadLicense", required = false) String ifUploadLicense,
                                   @RequestParam(value = "ifRecheck", required = false) String ifRecheck){
        HttpSession session = super.request.getSession();
        User user = (User) session.getAttribute(CramsConstants.SESSION_LOGIN_USER);
        String currentBankCode = user.getSbankcode();
        String userLevel = user.getSuserlevel();

        return workIndexService.queryRecordTotalNum(user.getSusercode(), approvalState,
                businessEmergency, userLevel, currentBankCode, ifUploadLicense, ifRecheck);
    }

    @RequestMapping(value = "/receipt", method = RequestMethod.GET)
    public void downloadReceipt(@RequestParam(value = "transactionNum") String transactionNum, HttpServletResponse response){
        WorkIndex result =workIndexService.selectByPrimaryKey(transactionNum);
//        List<String> fileList = imageService.selectProofNameByTranID(transactionNum);

        //商业银行行名
        String bankName = result.getSbankname();
        //提交的日期
        Date date = result.getSendtime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        calendar.setTime(date);
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        String hour = String.valueOf(calendar.get(Calendar.HOUR));
        String minute = String.valueOf(calendar.get(Calendar.MINUTE));
        //人行名称
        String pbcBankCode = result.getSpbcbankcode();
        Orga orga = orgaService.selectByPrimaryKey(pbcBankCode);
        String pbcBankName = orga.getSbankname();
        //开户人名称
        String unitName = result.getSdepositorname();
        //业务性质
        String businessCategory = result.getSbusinesscategory();
        //账号类型
        String accountType = result.getSaccounttype();
        //核准号
        String approvalCode = result.getSapprovalcode();
        //证书编号
        String identifier = result.getSidentifier();
        //通过的日期
        Date newDate = result.getScompletetimes();
        calendar.setTime(newDate);
        String newYear = String.valueOf(calendar.get(Calendar.YEAR));
        String newMonth = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        String newDay = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        Date expireDate = result.getSexpiretime();
        String expireStr = null;
        if (expireDate !=null ) {
            calendar.setTime(expireDate);
            String eYear = String.valueOf(calendar.get(Calendar.YEAR));
            String eMonth = String.valueOf(calendar.get(Calendar.MONTH) + 1);
            String eDay = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
            expireStr = eYear + "年" + eMonth + "月" + eDay + "日";
        }
        Map map = new HashMap<String, Object>();

        map.put("transactionNum", transactionNum);
        map.put("bankName", bankName!=null? bankName : "(空)");
        map.put("year", year);
        map.put("month", month);
        map.put("day", day);
        map.put("hour", hour);
        map.put("minute", minute);
        map.put("pbcBankName", pbcBankName);
        map.put("unitName", unitName);
        map.put("businessCategory", businessCategory);
        map.put("accountType", accountType);
        map.put("approvalCode", approvalCode);
        map.put("identifier", identifier);
        map.put("newYear", newYear);
        map.put("newMonth", newMonth);
        map.put("newDay", newDay);
        map.put("expireTime", expireStr);

        String baseDirectory = wordConfig.getBaseDirectory();
        String basePath = wordConfig.getBasePath();

        String filename;
        int ifNeedLicence = result.getSifneedlicence();

        switch (businessCategory) {
            case "存款人密码重置":
            case "临时户展期":
            case "注销久悬标志":
            case "撤销":
            case "专户现金支取": filename = businessCategory; break;
            default:
                if (ifNeedLicence == 1) {
                    filename = "账户核准";
                } else {
                    filename = "账户核准不换证";
                }
                break;
        }

        this.createWord(map, filename + ".ftl", baseDirectory,
                basePath + transactionNum, transactionNum+ ".pdf", response);

        this.writeLog(Logs.TRANS_RECIEPT_DOWNLOAD);
    }

    @RequestMapping(value = "/businessEmergency", method = RequestMethod.POST)
    public int updateBusinessEmergency(@RequestBody WorkIndex workIndex){
        this.writeLog(Logs.TRANS_UPDATE_EMERGENCY);
        return workIndexService.updateWorkIndexBusinessEmergency(workIndex);
    }

    @RequestMapping(value = "/operators", method = RequestMethod.GET)
    public HashMap queryOperators(@RequestParam(value = "transactionNum") String transactionNum){
        HashMap<String, Object> map = new HashMap<>();
        HttpSession session = super.request.getSession();
        User user = (User) session.getAttribute(CramsConstants.SESSION_LOGIN_USER);
        Orga orga = (Orga) session.getAttribute(CramsConstants.SESSION_ORGA_WITH_USER);
        String userCode = user.getSusercode();
        String bankCode = orga.getSbankcode();
        WorkIndex workIndex = workIndexService.selectByPrimaryKey(transactionNum);

        if (workIndex != null){
            String upUserCode = workIndex.getSupusercode();
            String reviewUserCode = workIndex.getSreviewusercode();
            String checkUserCode = workIndex.getScheckusercode();
            String recheckUserCode = workIndex.getSrecheckusercode();

            if (userCode.equals(upUserCode) || userCode.equals(reviewUserCode) ||
                    userCode.equals(checkUserCode) || userCode.equals(recheckUserCode) || userCode.equals("admin") ||
                    bankCode.equals(workIndex.getSpbcbankcode()) || bankCode.equals(workIndex.getSbankcode())){
                    String upUserName = workIndex.getSupusername();
                    String reviewName;
                    String checkName;
                    String recheckName = workIndex.getSrecheckusername();;

                    User reviewUser = userService.selectByPrimaryKey(reviewUserCode);
                    reviewName = reviewUser != null? reviewUser.getSusername() : null;

                    User checkUser = userService.selectByPrimaryKey(checkUserCode);
                    checkName = checkUser != null? checkUser.getSusername() : null;

                    map.put("upUserName", upUserName);
                    map.put("reviewName", reviewName);
                    map.put("checkName", checkName);
                    map.put("recheckName", recheckName);
            } else {
                map.put("error", "无权限查看经办人！");
            }
        } else {
            map.put("error", "该笔业务不存在！");
        }

        return map;
    }

    private String approvalState(String code){
        switch (code){
            case "0": return "待编辑";
            case "1": return "待复核";
            case "2": return "待审核";
//            case "4": return "待通过";
            case "3": return "已审核";
            case "4": return "被退回";
            case "5": return "业务终止";
        }
        return null;
    }

    private void createWord(Map<?, ?> dataMap, String templateName, String templateDirectory,
                                  String filePath, String fileName, HttpServletResponse response) {
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

        try {
            Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);

            configuration.setDefaultEncoding("UTF-8");

            // ftl模板文件统一放至 test.ftl包下面
            configuration.setDirectoryForTemplateLoading(ResourceUtils.getFile(templateDirectory));

            // 获取模板
            Template template = configuration.getTemplate(templateName);

//            File outFile = new File(filePath + File.separator + fileName);
//            if (!outFile.getParentFile().exists()) {
//                outFile.getParentFile().mkdirs();
//            }

            OutputStream ops = response.getOutputStream();

            // 将模板和数据模型合并生成文件
            Writer out = new BufferedWriter(new OutputStreamWriter(
                    ops, "UTF-8"));
            // 生成文件
            template.process(dataMap, out);

            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("## Error Information ##: {}", e);
        }

    }

}
