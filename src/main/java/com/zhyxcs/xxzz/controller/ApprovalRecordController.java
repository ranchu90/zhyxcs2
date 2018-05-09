package com.zhyxcs.xxzz.controller;

import com.zhyxcs.xxzz.domain.ApprovalRecord;
import com.zhyxcs.xxzz.domain.User;
import com.zhyxcs.xxzz.service.ApprovalRecordService;
import com.zhyxcs.xxzz.utils.CramsConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/approvalRecord")
public class ApprovalRecordController extends BaseController{
    @Autowired
    private ApprovalRecordService approvalRecordService;

    @RequestMapping(value = "/record", method = RequestMethod.POST)
    public int updateReview(@RequestBody ApprovalRecord record){
        HttpSession session = super.request.getSession();
        User user = (User) session.getAttribute(CramsConstants.SESSION_LOGIN_USER);

        record.setSapprovelusercode(user.getSusercode());
        record.setSapprovelusername(user.getSusername());

        System.out.println("result:" + record.getSapprovelresult());

        return approvalRecordService.insert(record);
    }

    @RequestMapping(value = "/record", method = RequestMethod.GET)
    public List<ApprovalRecord> getReview(@RequestParam(value = "transactionNum") String transactionNum){

        System.out.println("transactionNum:" + transactionNum);

        return approvalRecordService.selectByTranID(transactionNum);
    }
}
