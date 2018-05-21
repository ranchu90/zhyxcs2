package com.zhyxcs.xxzz.controller;

import com.github.pagehelper.PageInfo;
import com.zhyxcs.xxzz.domain.SystemLog;
import com.zhyxcs.xxzz.service.SystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class SystemLogContoller extends BaseController {
    @Autowired
    private SystemLogService systemLogService;

    @RequestMapping(value = "/systemlog", method = RequestMethod.GET)
    public Map<String, Object> querySystemLogByPageWithConditions(
            @RequestParam(value = "pageSize", required = false) String pageSize,
            @RequestParam(value = "currentPage", required = false) String currentPage,
            @RequestParam(value = "userCode", required = false) String userCode,
            @RequestParam(value = "userName", required = false) String userName,
            @RequestParam(value = "bankCode", required = false) String bankCode,
            @RequestParam(value = "bankName", required = false) String bankName,
            @RequestParam(value = "ipAddress", required = false) String ipAddress,
            @RequestParam(value = "comments", required = false) String comments,
            @RequestParam(value = "startTime", required = false) Date startTime,
            @RequestParam(value = "endTime", required = false) Date endTime) {
        Map<String, Object> map = new HashMap<String, Object>();
        pageSize = (pageSize == null || "".equals(pageSize.trim())) ? this.getDisplayCount() : pageSize;
        currentPage = (currentPage == null || "".equals(currentPage.trim())) ? "1" : currentPage;
        userCode = (userCode == null || "".equals(userCode.trim())) ? null : userCode;
        userName = (userName == null || "".equals(userName.trim())) ? null : userName;
        bankCode = (bankCode == null || "".equals(bankCode.trim())) ? null : bankCode;
        bankName = (bankName == null || "".equals(bankName.trim())) ? null : bankName;
        ipAddress = (ipAddress == null || "".equals(ipAddress.trim())) ? null : ipAddress;
        comments = (comments == null || "".equals(comments.trim())) ? null : comments;
        startTime = (startTime == null) ? null : startTime;
        endTime = (endTime == null) ? null : endTime;
        List<SystemLog> systemLogs = systemLogService.querySystemLogByPageWithConditions(userCode, userName, bankCode, bankName, ipAddress, comments, startTime, endTime);
        PageInfo<SystemLog> pageInfo = new PageInfo<SystemLog>();
        pageInfo.setList(systemLogs);
        map.put("pageInfo", pageInfo);
        return map;
    }
}
