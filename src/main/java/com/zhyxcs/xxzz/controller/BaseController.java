package com.zhyxcs.xxzz.controller;

import com.zhyxcs.xxzz.config.PageConfig;
import com.zhyxcs.xxzz.domain.Orga;
import com.zhyxcs.xxzz.domain.SystemLog;
import com.zhyxcs.xxzz.domain.User;
import com.zhyxcs.xxzz.service.SystemLogService;
import com.zhyxcs.xxzz.utils.CommonUtils;
import com.zhyxcs.xxzz.utils.CramsConstants;
import com.zhyxcs.xxzz.utils.UdpGetClientMacAddr;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BaseController {
    @Autowired
    protected HttpServletRequest request;
    @Autowired
    private SystemLogService systemLogService;
    @Autowired
    private PageConfig pageConfig;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //公平锁
    private Lock lock = new ReentrantLock(true);

    public String getDisplayCount(){
        return pageConfig.getDisplayCount();
    }


    protected void writeLog(String comments){
        HttpSession session=request.getSession();
        User user=(User)session.getAttribute(CramsConstants.SESSION_LOGIN_USER);
        Orga orga=(Orga)session.getAttribute(CramsConstants.SESSION_ORGA_WITH_USER);
        if(user==null||orga==null){
            return;
        }
        try {
            String ipAddress=CommonUtils.getIpAddress(request);
//            String macAddress=UdpGetClientMacAddr.getMac(ipAddress);
            SystemLog systemLog=new SystemLog();
            systemLog.setSusercode(user.getSusercode());
            systemLog.setSusename(user.getSusername());
            systemLog.setSbankcode(orga.getSbankcode());
            systemLog.setSbankname(orga.getSbankname());
            systemLog.setSipaddress(ipAddress);
            systemLog.setSmacaddress(null);
            systemLog.setSlogtime(CommonUtils.newDate());
            systemLog.setScomments(comments);

            lock.lock();
            systemLogService.insert(systemLog);
            lock.unlock();
        }catch (Exception e){
            e.printStackTrace();
            logger.error("## Error Information ##: {BaseController}", e);
        }
    }
}
