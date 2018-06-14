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
import java.util.HashMap;
import java.util.Map;

@WebListener
public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener {
    // 保存当前登录的所有用户
    public static Map<String, HttpSession> loginUser = new HashMap<>();

    private final static Logger logger = LoggerFactory.getLogger(SessionListener.class);

    public void sessionCreated(HttpSessionEvent httpSessionBindingEvent) {

    }

    public void sessionDestroyed(HttpSessionEvent httpSessionBindingEvent) {
        // 如果session超时, 则从map中移除这个用户
        try {
            User user = (User) httpSessionBindingEvent.getSession().getAttribute(CramsConstants.SESSION_LOGIN_USER);
            if (user != null) {
                loginUser.remove(user.getSusercode());
                System.out.println(user.getSusercode() + " destroyed!");
            }
        } catch (Exception e) {
            logger.error("## Error Information ##: {SessionListener}", e);
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
               }
            } catch (Exception e) {
                logger.error("## Error Information ##: {SessionListener}", e);
            }
        }
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {

    }
}
