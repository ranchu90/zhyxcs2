package com.zhyxcs.xxzz.controller.sv;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhyxcs.xxzz.config.ImageConfig;
import com.zhyxcs.xxzz.controller.BaseController;
import com.zhyxcs.xxzz.domain.*;
import com.zhyxcs.xxzz.service.SVImageService;
import com.zhyxcs.xxzz.service.SupervisionService;
import com.zhyxcs.xxzz.service.UserService;
import com.zhyxcs.xxzz.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.*;

@RestController
@RequestMapping("/api/supervision")
public class SupervisionController extends BaseController {
    @Autowired
    SupervisionService supervisionService;
    @Autowired
    SVImageService svImageService;
    @Autowired
    UserService userService;
    @Autowired
    private ImageConfig imageConfig;

    @RequestMapping(value = "/supervisions", method = RequestMethod.GET)
    public HashMap<String, Object> getSupervisionsByPages(@RequestParam(value = "pageSize") String pageSize,
                                                          @RequestParam(value = "currentPage") String currentPage,
                                                          @RequestParam(value = "approvalState") String approvalState,
                                                          @RequestParam(value = "bankCode", required = false) String bankCode,
                                                          @RequestParam(value = "depositorName", required = false) String depositorName,
                                                          @RequestParam(value = "businessType", required = false) String businessType){
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
                case "1":
                    svList = supervisionService.queryRecordByPageAndUserCodeBankEntry(pageSize, currentPage,
                            user.getSusercode(), approvalState, userLevel, currentBankCode, depositorName, businessType);
                    break;
                case "2":
                    svList = supervisionService.queryRecordByPageAndUserCodeBankCharge(pageSize, currentPage,
                            user.getSusercode(), approvalState, userLevel, currentBankCode, depositorName, businessType);
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
            case "0": return "待整改";
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
                !(action.equals(ActionType.RECHECK) || action.equals(ActionType.UPLOAD_LICENCE))){
            resultMap.put("error", "该业务已通过无法进行退回、撤销、终止操作！");
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
                case ActionType.COMMIT:
                    supervision.setSapprovalstate(ActionType.SV_APPROVAL_STATE_COMMERCE_REVIEW);
                    supervision.setSendtime(date);
                    break;
                case ActionType.COMMIT_REN:
                    supervision.setSapprovalstate(ActionType.SV_APPROVAL_STATE_PBC_CHECK);
                    supervision.setSendtime(date);
                    supervision.setScommittimes(date);
                    break;
                case ActionType.REVIEW:
                    supervision.setSapprovalstate(ActionType.SV_APPROVAL_STATE_PBC_CHECK);
                    supervision.setSreviewusercode(userCode);
                    supervision.setScommittimes(date);
                    break;
            }
        } catch (Exception e) {
//            logger.error("## Error Information ##: {}", e);
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
    public int queryRecordTotalNum(@RequestParam(value = "approvalState") String approvalState){
        HttpSession session = super.request.getSession();
        User user = (User) session.getAttribute(CramsConstants.SESSION_LOGIN_USER);
        String currentBankCode = user.getSbankcode();
        String userLevel = user.getSuserlevel();

        return supervisionService.queryRecordTotalNum(user.getSusercode(), userLevel, approvalState, currentBankCode);
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
