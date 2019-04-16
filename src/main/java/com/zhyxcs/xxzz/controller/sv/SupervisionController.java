package com.zhyxcs.xxzz.controller.sv;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhyxcs.xxzz.config.ImageConfig;
import com.zhyxcs.xxzz.controller.BaseController;
import com.zhyxcs.xxzz.domain.*;
import com.zhyxcs.xxzz.service.*;
import com.zhyxcs.xxzz.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@RestController
@RequestMapping("/api/supervision")
public class SupervisionController extends BaseController {
    @Autowired
    private SupervisionService supervisionService;
    @Autowired
    private SVImageService svImageService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrgaService orgaService;
    @Autowired
    private ImageConfig imageConfig;
    @Autowired
    private SupervisionBusinessStaticsService supervisionBusinessStaticsService;

    //公平锁
    private Lock lock = new ReentrantLock(true);

    @RequestMapping(value = "/supervision", method = RequestMethod.GET)
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

        Map<String, Object> map = new HashMap();
        String bankCode = orga.getSbankcode();

        List<String> pbcCodeList;
        List<String> bankCodeList;

        if (!"7".equals(user.getSuserlevel()) &&
                !"4".equals(user.getSuserlevel()) &&
                !"5".equals(user.getSuserlevel())) {
            bankCodeList = orgaService.getUnderBankcodeList(bankCode);
            pbcCodeList = null;
        } else {
            pbcCodeList = orgaService.getUnderBankcodeList(bankCode);
            bankCodeList = null;
        }

        try {
            PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
            List<Supervision> list = supervisionService.queryRecordByConditions(currentBankArea, currentCity, bankKind,
                    bankType, businessCategory, accountType, orgaCode, bankEntryUserCode, bankReviewUserCode,
                    renEntryUserCode, renRecheckUserCode, transactionNum, approvalCode, identifier, startTime, endTime,
                    bankCodeList, pbcCodeList);
            PageInfo<Supervision> supervisionPageInfo = new PageInfo(list);
            map.put("pageInfo", supervisionPageInfo);

//            this.writeLog(Logs.TRANS_QUERY);
        } catch (Exception e){
            e.printStackTrace();
//            logger.error("## Error Information ##: {}", e);
        }

        return map;
    }

    @RequestMapping(value = "/supervisions", method = RequestMethod.GET)
    public HashMap<String, Object> getSupervisionsByPages(@RequestParam(value = "pageSize") String pageSize,
                                                          @RequestParam(value = "currentPage") String currentPage,
                                                          @RequestParam(value = "approvalState") String approvalState,
                                                          @RequestParam(value = "bankCode", required = false) String bankCode,
                                                          @RequestParam(value = "depositorName", required = false) String depositorName,
                                                          @RequestParam(value = "businessType", required = false) String businessType,
                                                          @RequestParam(value = "kind", required = false) String kind){
        HttpSession session = super.request.getSession();
        User user = (User) session.getAttribute(CramsConstants.SESSION_LOGIN_USER);
        String currentBankCode = user.getSbankcode();
        String userLevel = user.getSuserlevel();

        List<Supervision> svList = null;

        pageSize = (pageSize == null || "".equals(pageSize.trim())) ? this.getDisplayCount() : pageSize;
        currentPage = (currentPage == null || "".equals(currentPage.trim())) ? "1" : currentPage;

        Map<String, Object> mapTest = new HashMap();
//        PageHelper.startPage(Integer.parseInt(currentPage), Integer.parseInt(pageSize));

        try {
            //判断用户的查询权限，根据用户级别
            switch (userLevel) {
                case "1": //商业银行录入
                    svList = supervisionService.queryRecordByPageAndUserCodeBankEntry(pageSize, currentPage,
                            user.getSusercode(), approvalState, userLevel, currentBankCode, depositorName, businessType,
                            kind);
                    break;
                case "2": //商业银行监督
                    svList = supervisionService.queryRecordByPageAndUserCodeBankCharge(pageSize, currentPage,
                            user.getSusercode(), approvalState, userLevel, currentBankCode, depositorName, businessType,
                            kind);
                    break;
                case "4": //人民银行监督
                    svList = supervisionService.queryRecordByPageAndUserCodeRenEntry(pageSize, currentPage,
                            user.getSusercode(), approvalState, userLevel, currentBankCode, bankCode, depositorName,
                            businessType, kind);
                    break;
                case "5": //人民银行复监督
                    svList = supervisionService.queryRecordByPageAndUserCodeRenCharge(pageSize, currentPage,
                            user.getSusercode(), approvalState, userLevel, currentBankCode, bankCode, depositorName,
                            businessType, kind);
                    break;
            }
        } catch (Exception e) {

        } finally {

        }

        PageInfo<WorkIndex> pageInfo = new PageInfo(svList);
        mapTest.put("pageInfo", pageInfo);

        List<Object> newSVList = new ArrayList<Object>();

        if (null != svList) {
            for (Supervision sv : svList){
                String approvelState = sv.getSapprovalstate();

                HashMap<String,Object> tempSV = new HashMap<String, Object>();
                Date recheckDate = sv.getSrechecktime();
                Date startDate = sv.getSstarttime();
                Date completeDate = sv.getScompletetimes();
                Date endDate = sv.getSendtime();
                Date commitDate = sv.getScommittimes();

                tempSV.put("srechecktime", recheckDate);
                tempSV.put("sstarttime", startDate);
                tempSV.put("scompletetimes", completeDate);
                tempSV.put("sendtime", endDate);
                tempSV.put("scommittimes", commitDate);
                tempSV.put("sapprovalstate", this.approvalState(approvelState));
                tempSV.put("sapprovalcode", sv.getSapprovalcode());
                tempSV.put("saccounttype", sv.getSaccounttype());
                tempSV.put("sbankcode", sv.getSbankcode());
                tempSV.put("sbankname", sv.getSbankname());
                tempSV.put("sbusinesscategory", sv.getSbusinesscategory());
                tempSV.put("scheckusercode", sv.getScheckusercode());
                tempSV.put("sdepositorname", sv.getSdepositorname());
                tempSV.put("srecheckopinion", sv.getSrecheckopinion());
                tempSV.put("srecheckresult", sv.getSrecheckresult());
                tempSV.put("sreviewusercode", sv.getSreviewusercode());
                tempSV.put("stransactionnum", sv.getStransactionnum());
                tempSV.put("supusercode", sv.getSupusercode());
                tempSV.put("supusername", sv.getSupusername());
                tempSV.put("sreturntimes", sv.getSreturntimes());
                tempSV.put("saccountnum", sv.getSaccountnum());
                tempSV.put("suniquesocialcreditcode", sv.getSuniquesocialcreditcode());

                newSVList.add(tempSV);
            }
        }

        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put("totalPages", pageInfo.getTotal());
        map.put("workIndexList", newSVList);

        return map;
    }

    private String approvalState(String code){
        switch (code){
            case "0": return "整改";
            case "1": return "待编辑";
            case "2": return "待复查";
//            case "4": return "待通过";
            case "3": return "待督查";
            case "4": return "待复督查";
            case "5": return "已完成";
        }
        return null;
    }

    @RequestMapping(value = "/supervision", method = RequestMethod.POST)
    public Supervision insert(@RequestBody Supervision supervision){
        HttpSession session = super.request.getSession(false);
        User user = (User) session.getAttribute(CramsConstants.SESSION_LOGIN_USER);
        Orga orga = (Orga) session.getAttribute(CramsConstants.SESSION_ORGA_WITH_USER);

        int result = 0;

        if (orga != null && user !=null) {

            supervision.setSbankcode(orga.getSbankcode());
            supervision.setSbankname(orga.getSbankname());
            supervision.setSupusername(user.getSusername());
            supervision.setSupusercode(user.getSusercode());

            try {

                result = supervisionService.newSupervision(supervision);
            } catch (Exception e){
                e.printStackTrace();

            }
        }

        if (null != supervision.getStransactionnum()){
            return supervision;
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/ApprovalState", method = RequestMethod.PUT)
    public HashMap updateSupervisionByApprovalState(@RequestBody Supervision supervision,
                                                  @RequestParam(value = "action") String action,
                                                  @RequestParam(value = "groundsId", required = false) String groundsId,
                                                  @RequestParam(value = "grounds", required = false) String grounds,
                                                  @RequestParam(value = "groundsState", required = false) String groundsState){
        HttpSession session = super.request.getSession();
        User user = (User) session.getAttribute(CramsConstants.SESSION_LOGIN_USER);
        String userCode = user.getSusercode();
        String userName = user.getSusername();
        HashMap resultMap = new HashMap();

        Supervision dbSupervision = supervisionService.selectByPrimaryKey(supervision.getStransactionnum());
        //如果数据库不存在此业务返回错误代码
        if (dbSupervision == null) {
            resultMap.put("error", "没有这笔业务！");
            return resultMap;
        }

        String dbApprovalState = dbSupervision.getSapprovalstate();

        //审核通过的业务只能被复审或上传许可证
        if (ActionType.SV_APPROVAL_STATE_NO_PASS.equals(dbApprovalState) &&
                !(action.equals(ActionType.SV_COMMIT))){
            resultMap.put("error", "被要求进行整改的业务只能进行提交和修改操作！");
            return resultMap;
        }

        //被终止的业务无法修改状态
        if (ActionType.SV_APPROVAL_STATE_END.equals(dbApprovalState)){
            resultMap.put("error", "该业务已被终止！");
            return resultMap;
        }

        Supervision tempSupervision;

        Date date = CommonUtils.newDate();
        try {
//            lock.lock();
            switch (action) {
                    //商业银行录入
                case ActionType.COMMIT:
                    supervision.setSapprovalstate(ActionType.SV_APPROVAL_STATE_COMMERCE_REVIEW);
                    supervision.setSendtime(date);
                    break;
                case ActionType.COMMIT_REN:
                    supervision.setSapprovalstate(ActionType.SV_APPROVAL_STATE_PBC_CHECK);
                    supervision.setSendtime(date);
                    supervision.setScommittimes(date);
                    break;
                    //商业银行的复查
                case ActionType.REVIEW:
                    supervision.setSapprovalstate(ActionType.SV_APPROVAL_STATE_PBC_CHECK);
                    supervision.setSreviewusercode(userCode);
                    supervision.setScommittimes(date);
                    break;
                case ActionType.SEND_BACK:
                    supervision.setSapprovalstate(ActionType.SV_APPROVAL_STATE_COMMERCE_NEW);
                    supervision.setSreviewusercode(userCode);
                    supervision.setSreturntimes(date);
                    break;
                    //人民银行的监督
                case ActionType.CHECK:
                    supervision.setSapprovalstate(ActionType.SV_APPROVAL_STATE_PBC_RECHECK);
                    supervision.setScheckusercode(userCode);
                    supervision.setScompletetimes(date);
                    //人民银行监督通过
                    supervisionBusinessStaticsService.insert(dbSupervision, AuditStatus.APPROVAL, OvertimeStatus.NOOVER, new GroundsForReturn(Long.valueOf(groundsId), grounds, groundsState));
                    break;
                case ActionType.RE_EDIT:
                    supervision.setSapprovalstate(ActionType.SV_APPROVAL_STATE_NO_PASS);
                    supervision.setScheckusercode(userCode);
                    supervision.setScompletetimes(date);
                    this.newSupervision(supervision.getStransactionnum());
                    //人民银行退回，插入退回理由
                    supervisionBusinessStaticsService.insert(dbSupervision, AuditStatus.UNTREAD, OvertimeStatus.NOOVER, new GroundsForReturn(Long.valueOf(groundsId), grounds, groundsState));
                    break;
                    //人民银行的复监督
                case ActionType.END:
                    supervision.setSapprovalstate(ActionType.SV_APPROVAL_STATE_END);
                    supervision.setSrecheckusercode(userCode);
                    supervision.setSrechecktime(date);
                    break;
            }
        } catch (Exception e) {
//            logger.error("## Error Information ##: {}", e);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           ·······················
        } finally {
//            lock.unlock();
        }

        this.writeLog(Logs.TRANS_UPDATE_APPROVALSTATE + ":" + action);

        int result = 0;

        try {
            result = supervisionService.updateApprovalStateNameByPrimaryKey(supervision, action);
            resultMap.put("success", "更新成功！");
        } catch (Exception e) {
//            logger.error("## Error Information ##: {}", e);
        }

        return resultMap;
    }

    private int copySVImagesWhileReturned(String transactionNum, String newtransactionNum){
        ArrayList<SupervisionImage> svImageLists = (ArrayList<SupervisionImage>) svImageService.selectImagesByTranID(transactionNum);

        //逐个复制图片
        for (int i=0; i<svImageLists.size(); ++i){
            SupervisionImage oldImage = svImageLists.get(i);
            String basePath = imageConfig.getSvBasePath();

            Date currentDate = CommonUtils.newDate();
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

            String datePath = format.format(currentDate);
            String yearMounthPath = datePath.substring(0, 6);
            String dayPath = datePath.substring(6);
            String imageName = String.valueOf(UUID.randomUUID());

            //年月-日-流水号
            String storePath = yearMounthPath + File.separatorChar + dayPath + File.separatorChar + newtransactionNum
                    + File.separatorChar + imageName;

            //image文件存储真实路径 = basePath + storePath

            File fileS = new File(basePath + oldImage.getSstorepath());
            File fileD = new File(basePath + storePath);

            if (!fileD.exists()) {

                if (!fileD.getParentFile().exists()) {
                    fileD.getParentFile().mkdirs();
                }

                try {
                    this.copyFileUsingStream(fileS, fileD);

                    oldImage.setSimagename(imageName);
                    oldImage.setSstorepath(storePath);
                    oldImage.setStransactionnum(newtransactionNum);
                    lock.lock();
                    svImageService.insert(oldImage);
                    this.writeLog(Logs.IMAGE_UPLOAD + " " + oldImage.toString());
                } catch (Exception e){
                    e.printStackTrace();
                    return 0;
                } finally {
                    lock.unlock();
                }
            }
        }
        return 1;
    }

    /*
    * 退回时，复制图片
    * */
    private void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }

    private int newSupervision(String transactionNum){
        Supervision supervision = supervisionService.selectByPrimaryKey(transactionNum);
        int length = transactionNum.length();
        String newtransactionNum = transactionNum;
        if (length == 24){
            newtransactionNum = transactionNum + "00";
        } else {
            String num = transactionNum.substring(24, 26);
            int tmp = Integer.parseInt(num);
            ++tmp;
            String tmpStr;
            if (tmp <10) {
                tmpStr = "0" + tmp;
            } else {
                tmpStr = "" + tmp;
            }
            newtransactionNum = transactionNum.substring(0, 24) + tmpStr;
        }

        Supervision newSupervision = new Supervision();
        newSupervision.setStransactionnum(newtransactionNum);
        newSupervision.setSaccountnum(supervision.getSaccountnum());
        newSupervision.setSdepositorname(supervision.getSdepositorname());
        newSupervision.setSuniquesocialcreditcode(supervision.getSuniquesocialcreditcode());
        newSupervision.setSkind("1");
        newSupervision.setSapprovalstate(ActionType.SV_APPROVAL_STATE_COMMERCE_NEW);
        newSupervision.setSbusinesscategory(supervision.getSbusinesscategory());
        newSupervision.setSaccounttype(supervision.getSaccounttype());
        newSupervision.setSbankcode(supervision.getSbankcode());
        newSupervision.setSpbcbankcode(supervision.getSpbcbankcode());
        newSupervision.setSbankname(supervision.getSbankname());
        newSupervision.setSupusercode(supervision.getSupusercode());
        newSupervision.setSupusername(supervision.getSupusername());
        newSupervision.setSstarttime(CommonUtils.newDate());

        //复制图片
        this.copySVImagesWhileReturned(transactionNum, newtransactionNum);

        return supervisionService.insert(newSupervision);
    }

    @RequestMapping(value = "/supervision", method = RequestMethod.DELETE)
    public int deleteWorkIndexByTranID(@RequestParam(value = "stransactionnum") String stransactionnum){
        Supervision supervision = supervisionService.selectByPrimaryKey(stransactionnum);

        HttpSession session = super.request.getSession(false);
        User user = (User) session.getAttribute(CramsConstants.SESSION_LOGIN_USER);
        String userCode = supervision.getSupusercode();
        String approvalState = supervision.getSapprovalstate();

        if (!userCode.equals(user.getSusercode()) || !(ActionType.SV_APPROVAL_STATE_COMMERCE_NEW.equals(approvalState))){
            this.writeLog(Logs.TRANS_DELETE_ILLEGAL + supervision.toString());
            return -1;
        }

        List<SupervisionImage> imageList = svImageService.selectImagesByTranID(stransactionnum);

        for (SupervisionImage image : imageList){

            if (image != null){
                String path = image.getSstorepath();
                String basePath = imageConfig.getSvBasePath();
                File file = new File(basePath + path);

                if (file != null && file.delete()){
                    svImageService.deleteByPrimaryKey(image.getSid());
                }
            }
        }

        this.writeLog(Logs.IMAGE_DELETE);
        this.writeLog(Logs.TRANS_DELETE + supervision.toString());
        return supervisionService.deleteByPrimaryKey(stransactionnum);
    }

    @RequestMapping(value = "/supervisionNum", method = RequestMethod.GET)
    public int queryRecordTotalNum(@RequestParam(value = "approvalState") String approvalState,
                                   @RequestParam(value = "kind", required = false) String kind){
        HttpSession session = super.request.getSession();
        User user = (User) session.getAttribute(CramsConstants.SESSION_LOGIN_USER);
        String currentBankCode = user.getSbankcode();
        String userLevel = user.getSuserlevel();

        return supervisionService.queryRecordTotalNum(user.getSusercode(), userLevel,
                approvalState, currentBankCode, kind);
    }

    @RequestMapping(value = "/operators", method = RequestMethod.GET)
    public HashMap queryOperators(@RequestParam(value = "transactionNum") String transactionNum){
        HashMap<String, Object> map = new HashMap<>();
        HttpSession session = super.request.getSession();
        User user = (User) session.getAttribute(CramsConstants.SESSION_LOGIN_USER);
        Orga orga = (Orga) session.getAttribute(CramsConstants.SESSION_ORGA_WITH_USER);
        String userCode = user.getSusercode();
        String bankCode = orga.getSbankcode();
        Supervision supervision = supervisionService.selectByPrimaryKey(transactionNum);

        if (supervision != null){
            String upUserCode = supervision.getSupusercode();
            String reviewUserCode = supervision.getSreviewusercode();
            String checkUserCode = supervision.getScheckusercode();
            String recheckUserCode = supervision.getSrecheckusercode();

            if (userCode.equals(upUserCode) || userCode.equals(reviewUserCode) ||
                    userCode.equals(checkUserCode) || userCode.equals(recheckUserCode) || userCode.equals("admin") ||
                    bankCode.equals(supervision.getSpbcbankcode()) || bankCode.equals(supervision.getSbankcode())){
                String upUserName = supervision.getSupusername();
                String reviewName;
                String checkName;
                String recheckName;

                User reviewUser = userService.selectByPrimaryKey(reviewUserCode);
                reviewName = reviewUser != null? reviewUser.getSusername() : null;

                User checkUser = userService.selectByPrimaryKey(checkUserCode);
                checkName = checkUser != null? checkUser.getSusername() : null;

                User recheckUser = userService.selectByPrimaryKey(recheckUserCode);
                recheckName = recheckUser != null? recheckUser.getSusername() : null;

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

    @RequestMapping(value = "/occupy", method = RequestMethod.GET)
    public HashMap occupyTransaction(@RequestParam(value = "transactionNum") String transactionNum){
        HttpSession session = super.request.getSession(false);
        User user = (User) session.getAttribute(CramsConstants.SESSION_LOGIN_USER);
        String userLevel = user.getSuserlevel();
        String userBankCode = user.getSbankcode();
        String userCode = user.getSusercode();

        HashMap result = new HashMap();

        Supervision supervision = supervisionService.selectByPrimaryKey(transactionNum);

        if (supervision != null){
            switch (userLevel) {
                case "2":
                    String reviewUserCode = supervision.getSreviewusercode();
                    if (!userBankCode.equals(supervision.getSbankcode()) || (reviewUserCode!=null && !reviewUserCode.equals(userCode))) {
                        result.put("error", "你没有权限操作该笔业务！");
                        return result;
                    } else if (reviewUserCode == null) {
                        supervision.setSreviewusercode(userCode);
                        supervisionService.occupyTransaction(supervision);
                    }

                    break;
                case "4":
                    String checkUserCode = supervision.getScheckusercode();
                    if (!userBankCode.equals(supervision.getSpbcbankcode()) || (checkUserCode!=null && !checkUserCode.equals(userCode))) {
                        result.put("error", "你没有权限操作该笔业务！");
                        return result;
                    } else if (checkUserCode == null) {
                        supervision.setScheckusercode(userCode);
                        supervisionService.occupyTransaction(supervision);
                    }
                    break;
                case "5":
                    String reCheckUserCode = supervision.getSrecheckusercode();
                    if (!userBankCode.equals(supervision.getSpbcbankcode()) || (reCheckUserCode!=null && !reCheckUserCode.equals(userCode))) {
                        result.put("error", "你没有权限操作该笔业务！");
                        return result;
                    } else if (reCheckUserCode == null) {
                        supervision.setSrecheckusercode(userCode);
                        supervisionService.occupyTransaction(supervision);
                    }
                    break;
            }

            result.put("success", "success");
        } else {
            result.put("error", "该流水号监督业务不存在");
        }

        return result;
    }
}
