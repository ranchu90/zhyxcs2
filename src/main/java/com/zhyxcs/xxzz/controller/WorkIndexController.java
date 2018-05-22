package com.zhyxcs.xxzz.controller;

import com.zhyxcs.xxzz.config.WordConfig;
import com.zhyxcs.xxzz.domain.Orga;
import com.zhyxcs.xxzz.domain.User;
import com.zhyxcs.xxzz.domain.WorkIndex;
import com.zhyxcs.xxzz.service.ImageService;
import com.zhyxcs.xxzz.service.WorkIndexService;
import com.zhyxcs.xxzz.utils.ActionType;
import com.zhyxcs.xxzz.utils.CramsConstants;
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
            return workIndex;
        } else {
            System.out.println("orga:" + orga);
            System.out.println("user:" + user);
        }


        return null;
    }

    @RequestMapping(value = "/workIndex", method = RequestMethod.GET)
    public List<WorkIndex> getWorkIndexes(){

        return workIndexService.selectAll();
    }

    @RequestMapping(value = "/workIndex", method = RequestMethod.DELETE)
    public int deleteWorkIndexByTranID(@RequestParam(value = "stransactionnum") String stransactionnum){

        return workIndexService.deleteByPrimaryKey(stransactionnum);
    }

    @RequestMapping(value = "/Depositor", method = RequestMethod.PUT)
    public int updateWorkIndexByDepositor(@RequestBody WorkIndex workIndex){

        return workIndexService.updateDepositorNameByPrimaryKey(workIndex);
    }

    @RequestMapping(value = "/ApprovalState", method = RequestMethod.PUT)
    public int updateWorkIndexByApprovalState(@RequestBody WorkIndex workIndex,
                                              @RequestParam(value = "action") String action){
        HttpSession session = super.request.getSession();
        User user = (User) session.getAttribute(CramsConstants.SESSION_LOGIN_USER);
        String userCode = user.getSusercode();
        String userName = user.getSusername();
        String level = user.getSuserlevel();

        switch (action){
            case ActionType.COMMIT:
                workIndex.setSendtime(new Date());
                break;
            case ActionType.CALL_BACK:
                break;
            case ActionType.SEND_BACK:
                if (Integer.valueOf(level) >= 4){
                    int time = workIndex.getSreturntimes() + 1;
                    workIndex.setSreturntimes(time);
                }
                break;
            case ActionType.REVIEW:
                workIndex.setSreviewusercode(userCode);
                break;
            case ActionType.CHECK:
                workIndex.setScheckusercode(userCode);
                break;
            case ActionType.RECHECK:
                workIndex.setSrecheckusercode(userCode);
                workIndex.setSrecheckusername(userName);
                workIndex.setSrechecktime(new Date());
                break;
            case ActionType.UPLOAD_LICENCE:break;
        }

        return workIndexService.updateApprovalStateNameByPrimaryKey(workIndex, action);
    }

    @RequestMapping(value = "/ApprovalCode", method = RequestMethod.PUT)
    public int updateWorkIndexByApprovalCodeAndIdentifier(@RequestBody WorkIndex workIndex){
        int code = workIndexService.updateWorkIndexByApprovalCodeAndIdentifier(workIndex);

        if (code <= 0){
            return 0;
        }

        return 1;
    }

    @RequestMapping(value = "/workIndexes", method = RequestMethod.GET)
    public HashMap<String, Object> getWorkIndexesByPage(@RequestParam(value = "pageSize") String pageSize,
                                                        @RequestParam(value = "currentPage") String currentPage,
                                                        @RequestParam(value = "approvalState") String approvalState,
                                                        @RequestParam(value = "businessEmergency") String businessEmergency){

        HttpSession session = super.request.getSession();
        User user = (User) session.getAttribute(CramsConstants.SESSION_LOGIN_USER);
        String currentBankCode = user.getSbankcode();
        String userLevel = user.getSuserlevel();
        int totalPages = workIndexService.queryRecordTotalNum(user.getSusercode(), approvalState,
                businessEmergency, userLevel, currentBankCode);

        List<WorkIndex> workIndexList = null;

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
                        currentPage, user.getSusercode(), approvalState, userLevel, businessEmergency, currentBankCode);
                break;
            case "5":
                workIndexList = workIndexService.queryRecordByPageAndUserCodeRenCharge(pageSize,
                        currentPage, user.getSusercode(), approvalState, userLevel, businessEmergency, currentBankCode);
                break;
            case "7":
                workIndexList = workIndexService.queryRecordByPageAndUserCodeRenAdmin(pageSize,
                        currentPage, user.getSusercode(), approvalState, userLevel, businessEmergency);
                break;
        }


        List<Object> newWorkIndexList = new ArrayList<Object>();

        for (WorkIndex workIndex : workIndexList){
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

            newWorkIndexList.add(workTemp);
        }

        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put("totalPages", Integer.valueOf(totalPages));
        map.put("workIndexList", newWorkIndexList);

        return map;
    }

    @RequestMapping(value = "/workIndexNum", method = RequestMethod.GET)
    public int queryRecordTotalNum(@RequestParam(value = "approvalState") String approvalState,
                                   @RequestParam(value = "businessEmergency") String businessEmergency){
        HttpSession session = super.request.getSession();
        User user = (User) session.getAttribute(CramsConstants.SESSION_LOGIN_USER);
        String currentBankCode = user.getSbankcode();
        String userLevel = user.getSuserlevel();

        return workIndexService.queryRecordTotalNum(user.getSusercode(), approvalState,
                businessEmergency, userLevel, currentBankCode);
    }

    @RequestMapping(value = "/receipt", method = RequestMethod.GET)
    public void downloadReceipt(@RequestParam(value = "transactionNum") String transactionNum, HttpServletResponse response){
        WorkIndex result =workIndexService.selectByPrimaryKey(transactionNum);
        List<String> fileList = imageService.selectProofNameByTranID(transactionNum);

        String bankName = result.getSbankname();
        String checkBankName = result.getScheckusercode();
        Calendar calendar = Calendar.getInstance();
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));

        Map map = new HashMap<String, Object>();
        map.put("bankName", bankName!=null? bankName : "(空)");
        map.put("checkBankName", "中国人民银行长沙中心支行");
        map.put("transactionNum", transactionNum);
        map.put("unitName", result.getSdepositorname());
        map.put("accountType", result.getSaccounttype());
        map.put("businessCategory", result.getSbusinesscategory());
        map.put("approvalCode", result.getSapprovalcode());
        map.put("identifier", result.getSidentifier());
        map.put("year", year);
        map.put("month", month);
        map.put("day", day);
        map.put("fileList", fileList != null? fileList : "(空)");

        String baseDirectory = wordConfig.getBaseDirectory();
        String basePath = wordConfig.getBasePath();

        this.createWord(map, "receipt.ftl", baseDirectory,
                basePath + transactionNum, transactionNum+ ".doc", response);
    }

    @RequestMapping(value = "/businessEmergency", method = RequestMethod.POST)
    public int updateBusinessEmergency(@RequestBody WorkIndex workIndex){
        return workIndexService.updateWorkIndexBusinessEmergency(workIndex);
    }

    private String approvalState(String code){
        switch (code){
            case "0": return "被退回";
            case "1": return "待编辑";
            case "2": return "待复核";
            case "3": return "待审核";
//            case "4": return "待通过";
            case "4": return "已通过";
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
