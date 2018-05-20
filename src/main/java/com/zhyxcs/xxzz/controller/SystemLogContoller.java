package com.zhyxcs.xxzz.controller;

import com.zhyxcs.xxzz.domain.SystemLog;
import com.zhyxcs.xxzz.service.SystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/systemlog")
public class SystemLogContoller extends BaseController {
    @Autowired
    private SystemLogService systemLogService;

    @RequestMapping(value = "query",method = RequestMethod.GET)
    public List<SystemLog> querySystemLogByPageWithConditions(
            @RequestParam(value = "pageSize") String pageSize,
            @RequestParam(value = "currentPage") String currentPage,
            @RequestParam(value = "userCode") String userCode,
            @RequestParam(value = "userName") String userName,
            @RequestParam(value = "bankCode") String bankCode,
            @RequestParam(value = "bankName") String bankName,
            @RequestParam(value = "ipAddress") String ipAddress,
            @RequestParam(value = "comments") String comments,
            @RequestParam(value = "startTime") Date startTime,
            @RequestParam(value = "endTime") Date endTime){
        return systemLogService.querySystemLogByPageWithConditions(pageSize,currentPage,userCode,userName,bankCode,bankName,ipAddress,comments,startTime,endTime);
    }
}
