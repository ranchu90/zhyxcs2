package com.zhyxcs.xxzz.controller;

import com.zhyxcs.xxzz.domain.Orga;
import com.zhyxcs.xxzz.domain.User;
import com.zhyxcs.xxzz.domain.WorkIndex;
import com.zhyxcs.xxzz.service.WorkIndexService;
import com.zhyxcs.xxzz.utils.CramsConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class WorkIndexController extends BaseController{
    @Autowired
    private WorkIndexService workIndexService;

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

    @RequestMapping(value = "/workIndex/Depositor", method = RequestMethod.PUT)
    public int updateWorkIndexByDepositor(@RequestBody WorkIndex workIndex){

        return workIndexService.updateDepositorNameByPrimaryKey(workIndex);
    }

    @RequestMapping(value = "/workIndex/ApprovalState", method = RequestMethod.PUT)
    public int updateWorkIndexByApprovalState(@RequestBody WorkIndex workIndex){

        return workIndexService.updateApprovalStateNameByPrimaryKey(workIndex);
    }

    @RequestMapping(value = "/workIndexes", method = RequestMethod.GET)
    public HashMap<String, Object> getWorkIndexesByPage(@RequestParam(value = "pageSize") String pageSize,
                                                    @RequestParam(value = "currentPage") String currentPage,
                                                        @RequestParam(value = "approvalState") String approvalState){

        HttpSession session = super.request.getSession();
        User user = (User) session.getAttribute(CramsConstants.SESSION_LOGIN_USER);
        String userLevel = user.getSuserlevel();
        int totalPages = workIndexService.queryRecordTotalNum(user.getSusercode(), approvalState);
        List<WorkIndex> workIndexList = workIndexService.queryRecordByPageAndUserCode(pageSize,
                currentPage, user.getSusercode(), approvalState, userLevel);
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
            workTemp.put("srecheckopition", workIndex.getSrecheckopition());
            workTemp.put("srecheckresult", workIndex.getSrecheckresult());
            workTemp.put("srecheckusercode", workIndex.getSrecheckusercode());
            workTemp.put("srecheckusername", workIndex.getSrecheckusername());
            workTemp.put("sreviewusercode", workIndex.getSreviewusercode());
            workTemp.put("stransactionnum", workIndex.getStransactionnum());
            workTemp.put("supusercode", workIndex.getSupusercode());
            workTemp.put("supusername", workIndex.getSupusername());

            newWorkIndexList.add(workTemp);
        }

        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put("totalPages", Integer.valueOf(totalPages));
        map.put("workIndexList", newWorkIndexList);

        return map;
    }

    private String approvalState(String code){
        switch (code){
            case "1": return "待编辑";
            case "2": return "待复核";
            case "3": return "待审核";
//            case "4": return "待通过";
            case "4": return "已通过";
        }
        return null;
    }
}
