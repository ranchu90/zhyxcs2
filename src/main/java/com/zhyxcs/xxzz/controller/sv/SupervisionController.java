package com.zhyxcs.xxzz.controller.sv;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhyxcs.xxzz.controller.BaseController;
import com.zhyxcs.xxzz.domain.Orga;
import com.zhyxcs.xxzz.domain.Supervision;
import com.zhyxcs.xxzz.domain.User;
import com.zhyxcs.xxzz.domain.WorkIndex;
import com.zhyxcs.xxzz.service.SupervisionService;
import com.zhyxcs.xxzz.utils.CramsConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@RequestMapping("/api/supervision")
public class SupervisionController extends BaseController {
    @Autowired
    SupervisionService supervisionService;

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
            workTemp.put("sapprovalstate", approvelState);
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
}
