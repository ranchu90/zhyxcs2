package com.zhyxcs.xxzz.config;

import com.zhyxcs.xxzz.domain.User;
import com.zhyxcs.xxzz.service.UserService;
import com.zhyxcs.xxzz.utils.CramsConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@WebListener
public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener {
    // 保存当前登录的所有用户
    public static ConcurrentHashMap<String, HttpSession> loginUser = new ConcurrentHashMap<>();

    private final static Logger logger = LoggerFactory.getLogger(SessionListener.class);

    public void sessionCreated(HttpSessionEvent httpSessionBindingEvent) {

    }

    public void sessionDestroyed(HttpSessionEvent httpSessionBindingEvent) {
        // 如果session超时, 则从map中移除这个用户
        try {
            User user = (User) httpSessionBindingEvent.getSession().getAttribute(CramsConstants.SESSION_LOGIN_USER);
            if (user != null) {
                loginUser.remove(user.getSusercode());
                String info = (new Date()).toString() + " - " + user.getSusercode() + " 销毁!" + " 在线人数：" + loginUser.size();
                System.out.println(info);
                logger.info(info);
            }
        } catch (Exception e) {
            logger.error("## Session Error Information ##: {SessionListener}", e);
        }
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        // 如果添加的属性是用户名, 则加入map中
        if (httpSessionBindingEvent.getName().equals(CramsConstants.SESSION_LOGIN_USER)) {

            User user = (User) httpSessionBindingEvent.getValue();
            if (user.getSusercode().equals("admin")){

            } else {
                HttpSession session = loginUser.remove(user.getSusercode());
                if (session != null) {
                    session.removeAttribute(CramsConstants.SESSION_LOGIN_USER);
                }
                loginUser.put(user.getSusercode(), httpSessionBindingEvent.getSession());
                String info = (new Date()).toString() + " - " + user.getSusercode() + " 登陆!" + " 在线人数：" + loginUser.size();
                System.out.println(info);
                logger.info(info);
            }
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
        // 如果移除的属性是用户名, 则从map中移除
        if (httpSessionBindingEvent.getName().equals(CramsConstants.SESSION_LOGIN_USER)) {
            try {
                User user = (User) httpSessionBindingEvent.getValue();
               if (user != null) {
                   loginUser.remove(user.getSusercode());
                   String info = (new Date()).toString() + " - " + user.getSusercode() + " 退出!" + " 在线人数：" + loginUser.size();
                   System.out.println(info);
                   logger.info(info);
               }
            } catch (Exception e) {
                logger.error("## Session Error Information ##: {SessionListener}", e);
            }
        }
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {

    }
}
