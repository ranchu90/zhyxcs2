package com.zhyxcs.xxzz.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.corba.se.spi.orbutil.threadpool.Work;
import com.zhyxcs.xxzz.config.WordConfig;
import com.zhyxcs.xxzz.domain.*;
import com.zhyxcs.xxzz.service.*;
import com.zhyxcs.xxzz.utils.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
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
    private ApprovalRecordService approvalRecordService;

    @Autowired
    private BusinessStatisticsService businessStatisticsService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private WordConfig wordConfig;

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
            workIndexService.newWorkIndex(workIndex);

            this.writeLog(Logs.TRANS_NEW_SUCCESS);
            return workIndex;
        } else {
            System.out.println("orga:" + orga);
            System.out.println("user:" + user);
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
                                          @RequestParam(value = "startTime", required = false) String startTime,
                                          @RequestParam(value = "endTime", required = false) String endTime){
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
        startTime = (startTime == null || "".equals(startTime)) ? null : startTime;
        endTime = (endTime == null || "".equals(endTime)) ? null : endTime;

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
        PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
        List<WorkIndex> list = workIndexService.queryRecordByConditions(currentBankArea, currentCity, bankKind, bankType, businessCategory,
                accountType, orgaCode, bankEntryUserCode, bankReviewUserCode, renEntryUserCode, renRecheckUserCode, transactionNum,
                approvalCode, identifier, startTime, endTime);
        PageInfo<WorkIndex> workIndexPageInfo = new PageInfo(list);
        map.put("pageInfo", workIndexPageInfo);

        this.writeLog(Logs.TRANS_QUERY);

        return map;
    }

    @RequestMapping(value = "/workIndex", method = RequestMethod.DELETE)
    public int deleteWorkIndexByTranID(@RequestParam(value = "stransactionnum") String stransactionnum){
        this.writeLog(Logs.TRANS_DELETE);
        return workIndexService.deleteByPrimaryKey(stransactionnum);
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

        switch (action){
            case ActionType.COMMIT:
                workIndex.setSendtime(new Date());
                break;
            case ActionType.COMMIT_REN:
                workIndex.setSendtime(new Date());
                workIndex.setScommittimes(new Date());
                break;
            case ActionType.CALL_BACK:
                break;
            case ActionType.SEND_BACK:
                workIndex.setSreturntimes(new Date());
                break;
            case ActionType.SEND_BACK_REN:
                workIndex.setSpbcreturntimes(new Date());
                tempWorkIndex = workIndexService.selectByPrimaryKey(workIndex.getStransactionnum());
                tempWorkIndex.setSpbcreturntimes(workIndex.getSpbcreturntimes());
                //人民银行退回
                businessStatisticsService.insert(tempWorkIndex, AuditStatus.UNTREAD, OvertimeStatus.NOOVER, new GroundsForReturn(Long.valueOf(groundsId), grounds, groundsState));
                tempWorkIndex = null;
                break;
            case ActionType.REVIEW:
                workIndex.setSreviewusercode(userCode);
                workIndex.setScommittimes(new Date());
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
                workIndex.setSrechecktime(new Date());
                break;
            case ActionType.UPLOAD_LICENCE:
                workIndex.setSuploadlicense(1);
                break;
            case ActionType.END:
                workIndex.setScompletetimes(new Date());
                tempWorkIndex = workIndexService.selectByPrimaryKey(workIndex.getStransactionnum());
                tempWorkIndex.setScompletetimes(workIndex.getScompletetimes());
                //人民银行中止
                businessStatisticsService.insert(workIndex, AuditStatus.UNTREAD, OvertimeStatus.NOOVER, new GroundsForReturn(Long.valueOf(groundsId), grounds, groundsState));
                tempWorkIndex = null;
                break;
        }

        this.writeLog(Logs.TRANS_UPDATE_APPROVALSTATE + ":" + action);

        return workIndexService.updateApprovalStateNameByPrimaryKey(workIndex, action);
    }

    @RequestMapping(value = "/ApprovalCode", method = RequestMethod.PUT)
    public int updateWorkIndexByApprovalCodeAndIdentifier(@RequestBody WorkIndex workIndex){
        workIndex.setScompletetimes(new Date());
        int code = workIndexService.updateWorkIndexByApprovalCodeAndIdentifier(workIndex);

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

        //判断用户的查询权限，根据用户级别
        switch (userLevel){
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

        PageInfo<WorkIndex> pageInfo = new PageInfo(workIndexList);
        mapTest.put("pageInfo", pageInfo);

        List<WorkIndex> tempList = pageInfo.getList();

        List<Object> newWorkIndexList = new ArrayList<Object>();

        for (WorkIndex workIndex : tempList){
            String approvelState = workIndex.getSapprovalstate();
            Date startDate = workIndex.getSstarttime();
            Date checkDate = workIndex.getSrechecktime();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String startTime = startDate!=null? format.format(startDate) : null;
            String checkTime = checkDate!=null? format.format(checkDate) : null;

            HashMap<String,Object> workTemp = new HashMap<String, Object>();

            workTemp.put("srechecktime", checkTime);
            workTemp.put("sstarttime", startTime);
            workTemp.put("sapprovalstate", this.approvalState(approvelState));
            workTemp.put("sapprovalcode", workIndex.getSapprovalcode());
            workTemp.put("saccounttype", workIndex.getSaccounttype());
            workTemp.put("sbankcode", workIndex.getSbankcode());
            workTemp.put("sbankname", workIndex.getSbankname());
            workTemp.put("sbusinesscategory", workIndex.getSbusinesscategory());
            workTemp.put("scheckusercode", workIndex.getScheckusercode());
            workTemp.put("sdepositorname", workIndex.getSdepositorname());
            workTemp.put("sendtime", workIndex.getSendtime());
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

            newWorkIndexList.add(workTemp);
        }

        this.writeLog(Logs.TRANS_QUERY_PAGES);

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
        List<String> fileList = imageService.selectProofNameByTranID(transactionNum);

        //商业银行行名
        String bankName = result.getSbankname();
        //提交的日期
        Date date = result.getSendtime();
        Calendar calendar = Calendar.getInstance();
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
        //开户性质
        String accountType = result.getSaccounttype();
        //核准号
        String approvalCode = result.getSapprovalcode();
        //证书编号
        String identifier = result.getSidentifier();
        //通过的日期
        String checkUserCode = result.getScheckusercode();
        ApprovalRecord approvalRecord = approvalRecordService.selectByUserCodeAndOpinion(transactionNum,
                checkUserCode, "审核已通过");
        Date newDate = approvalRecord.getSapproveltime();
        calendar.setTime(newDate);
        String newYear = String.valueOf(calendar.get(Calendar.YEAR));
        String newMonth = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        String newDay = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
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
        map.put("accountType", accountType);
        map.put("approvalCode", approvalCode);
        map.put("identifier", identifier);
        map.put("newYear", newYear);
        map.put("newMonth", newMonth);
        map.put("newDay", newDay);

        String baseDirectory = wordConfig.getBaseDirectory();
        String basePath = wordConfig.getBasePath();

        this.createWord(map, "shu.ftl", baseDirectory,
                basePath + transactionNum, transactionNum+ ".doc", response);

        this.writeLog(Logs.TRANS_RECIEPT_DOWNLOAD);
    }

    @RequestMapping(value = "/businessEmergency", method = RequestMethod.POST)
    public int updateBusinessEmergency(@RequestBody WorkIndex workIndex){
        this.writeLog(Logs.TRANS_UPDATE_EMERGENCY);
        return workIndexService.updateWorkIndexBusinessEmergency(workIndex);
    }

    private String approvalState(String code){
        switch (code){
            case "0": return "待编辑";
            case "1": return "待复核";
            case "2": return "待审核";
//            case "4": return "待通过";
            case "3": return "已通过";
            case "4": return "被退回";
            case "5": return "业务中止";
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
        }
    }

}
