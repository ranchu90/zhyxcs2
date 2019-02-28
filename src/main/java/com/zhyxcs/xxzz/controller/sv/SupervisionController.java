package com.zhyxcs.xxzz.controller.sv;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhyxcs.xxzz.config.ImageConfig;
import com.zhyxcs.xxzz.controller.BaseController;
import com.zhyxcs.xxzz.domain.*;
import com.zhyxcs.xxzz.service.SVImageService;
import com.zhyxcs.xxzz.service.SupervisionService;
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
//        String currentBankCode = user.getSbankcode();
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
                    svList = supervisionService.queryRecordByPageAndUserCodeBankEntry(pageSize, currentPage, user.getSusercode(), approvalState,
                            userLevel, bankCode, depositorName, businessType);
                    break;
            }
        } catch (Exception e) {

        } finally {

        }

        PageInfo<WorkIndex> pageInfo = new PageInfo(svList);
        mapTest.put("pageInfo", pageInfo);

        List<Object> newSVList = new ArrayList<Object>();

        for (Supervision sv : svList){
            String approvelState = sv.getSapprovalstate();

            HashMap<String,Object> workTemp = new HashMap<String, Object>();
            Date recheckDate = sv.getSrechecktime();
            Date startDate = sv.getSstarttime();
            Date completeDate = sv.getScompletetimes();
            Date endDate = sv.getSendtime();
            Date commitDate = sv.getScommittimes();

            workTemp.put("srechecktime", recheckDate);
            workTemp.put("sstarttime", startDate);
            workTemp.put("scompletetimes", completeDate);
            workTemp.put("sendtime", endDate);
            workTemp.put("scommittimes", commitDate);
            workTemp.put("sapprovalstate", this.approvalState(approvelState));
            workTemp.put("sapprovalcode", sv.getSapprovalcode());
            workTemp.put("saccounttype", sv.getSaccounttype());
            workTemp.put("sbankcode", sv.getSbankcode());
            workTemp.put("sbankname", sv.getSbankname());
            workTemp.put("sbusinesscategory", sv.getSbusinesscategory());
            workTemp.put("scheckusercode", sv.getScheckusercode());
            workTemp.put("sdepositorname", sv.getSdepositorname());
            workTemp.put("srecheckopinion", sv.getSrecheckopinion());
            workTemp.put("srecheckresult", sv.getSrecheckresult());
            workTemp.put("sreviewusercode", sv.getSreviewusercode());
            workTemp.put("stransactionnum", sv.getStransactionnum());
            workTemp.put("supusercode", sv.getSupusercode());
            workTemp.put("supusername", sv.getSupusername());
            workTemp.put("sreturntimes", sv.getSreturntimes());

            newSVList.add(workTemp);
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
}
